package com.tulau.core.views.my_simple_rating_bar

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import com.tulau.core.R

/**
 * Created by Tư Lầu on 2/28/18.
 */
class RotationRatingBar : AnimationRatingBar {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun emptyRatingBar() { // Need to remove all previous runnable to prevent emptyRatingBar and fillRatingBar out of sync
        if (mRunnable != null) {
            mHandler!!.removeCallbacksAndMessages(mRunnableToken)
        }
        var delay: Long = 0
        for (partialView in mPartialViews!!) {
            mHandler!!.postDelayed({ partialView.setEmpty() }, 5.let { delay += it; delay })
        }
    }

    override fun fillRatingBar(rating: Float) { // Need to remove all previous runnable to prevent emptyRatingBar and fillRatingBar out of sync
        if (mRunnable != null) {
            mHandler!!.removeCallbacksAndMessages(mRunnableToken)
        }
        for (partialView in mPartialViews!!) {
            val ratingViewId = partialView.tag as Int
            val maxIntOfRating = Math.ceil(rating.toDouble())
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
                val rotation = AnimationUtils.loadAnimation(context, R.anim.rotation_rating_bar)
                partialView.startAnimation(rotation)
            }
        }
    }

    companion object {
        // Control animation speed
        private const val ANIMATION_DELAY: Long = 15
    }
}