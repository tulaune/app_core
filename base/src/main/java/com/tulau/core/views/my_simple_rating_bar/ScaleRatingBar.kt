package com.tulau.core.views.my_simple_rating_bar

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import com.tulau.core.R

/**
 * Created by Tư Lầu on 2/28/18.
 */
class ScaleRatingBar : AnimationRatingBar {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {}

    override fun emptyRatingBar() { // Need to remove all previous runnable to prevent emptyRatingBar and fillRatingBar out of sync
        mHandler?.removeCallbacksAndMessages(mRunnableToken)
        var delay: Long = 0
        for (view in mPartialViews!!) {
            mHandler?.postDelayed({ view.setEmpty() }, 5.let { delay += it; delay })
        }
    }

    override fun fillRatingBar(rating: Float) { // Need to remove all previous runnable to prevent emptyRatingBar and fillRatingBar out of sync
        if (mRunnable != null) {
            mHandler?.removeCallbacksAndMessages(mRunnableToken)
        }
        for (partialView in mPartialViews!!) {
            val ratingViewId = partialView.tag as Int
            val maxIntOfRating = kotlin.math.ceil(rating.toDouble())
            if (ratingViewId > maxIntOfRating) {
                partialView.setEmpty()
                continue
            }
            mRunnable = getAnimationRunnable(rating, partialView, ratingViewId, maxIntOfRating)
            postRunnable(mRunnable!!, ANIMATION_DELAY)
        }
    }

    private fun getAnimationRunnable(rating: Float, partialView: PartialView, ratingViewId: Int, maxIntOfRating: Double): Runnable {
        return Runnable {
            if (ratingViewId.toDouble() == maxIntOfRating) {
                partialView.setPartialFilled(rating)
            } else {
                partialView.setFilled()
            }
            if (ratingViewId.toFloat() == rating) {
                val scaleUp = AnimationUtils.loadAnimation(context, R.anim.scale_up_rating_bar)
                val scaleDown = AnimationUtils.loadAnimation(context, R.anim.scale_down_rating_bar)
                partialView.startAnimation(scaleUp)
                partialView.startAnimation(scaleDown)
            }
        }
    }

    companion object {
        // Control animation speed
        private const val ANIMATION_DELAY: Long = 15
    }
}