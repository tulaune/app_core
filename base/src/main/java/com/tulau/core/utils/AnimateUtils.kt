package com.tulau.core.utils

import android.content.Context
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import com.tulau.core.R

/**
 * Created by Dragon on 12/23/16.
 */

object AnimateUtils {

    val ANIM_DURATION = 100
    val ANIM_DURATION_X2 = 200
    val ANIM_DURATION_X3 = 300
    val ANIM_DURATION_X4 = 400
    val ANIM_DURATION_X5 = 500


    fun slideInBottom(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom)
        animation.duration = duration.toLong()
        view.visibility = View.VISIBLE
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)
    }

    fun slideInRight(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
        animation.duration = duration.toLong()
        view.visibility = View.VISIBLE
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)
    }

    fun slideInLeft(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
        animation.duration = duration.toLong()
        view.visibility = View.VISIBLE
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)
    }

    fun slideInTop(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_top)
        animation.duration = duration.toLong()
        view.visibility = View.VISIBLE
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)
    }

    fun slideOutBottom(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_bottom)
        animation.duration = duration.toLong()
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)

    }

    fun slideOutTop(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_top)
        animation.duration = duration.toLong()
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)

    }

    fun slideOutLeft(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_left)
        animation.duration = duration.toLong()
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)

    }

    fun slideOutRight(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_right)
        animation.duration = duration.toLong()
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)

    }

    fun slideFromTo(view: View, duration: Int, xFro: Int, xTo: Int, yFro: Int, yTo: Int, callBack: CallBackAnimation?) {
        val trans = TranslateAnimation(xFro.toFloat(), xTo.toFloat(), yFro.toFloat(), yTo.toFloat())
        trans.duration = duration.toLong()
        trans.fillAfter = true// stop the animation at the end position
        trans.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                callBack?.onAnimationEnd()

            }
        })
        view.startAnimation(trans)
    }

    fun fadeIn(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation.duration = duration.toLong()
        view.visibility = View.VISIBLE
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)
    }

    fun fadeOut(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        animation.duration = duration.toLong()
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)
    }

    fun slideFadeInRight(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_fade_right_in)
        animation.duration = duration.toLong()
        view.visibility = View.VISIBLE
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)
    }

    fun slideFadeOutRight(view: View, context: Context, duration: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.slide_fade_right_out)
        animation.duration = duration.toLong()
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                view.visibility = View.GONE
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        view.startAnimation(animation)
    }

    fun startMyAnim(view: View, context: Context, duration: Int, reSourceID: Int, callBack: CallBackAnimation?) {
        val animation = AnimationUtils.loadAnimation(context, reSourceID)
        animation.duration = duration.toLong()
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                callBack?.onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        view.startAnimation(animation)
    }


    //Animation in tab - start
    fun inFromRightAnimation(animDuration: Int): Animation {
        val inFromRight = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,
                1.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f
        )
        return setProperties(inFromRight, animDuration)
    }

    fun outToRightAnimation(animDuration: Int): Animation {
        val outToRight = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,
                0.0f,
                Animation.RELATIVE_TO_PARENT,
                1.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f
        )
        return setProperties(outToRight, animDuration)
    }

    fun inFromLeftAnimation(animDuration: Int): Animation {
        val inFromLeft = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,
                -1.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f
        )
        return setProperties(inFromLeft, animDuration)
    }

    fun outToLeftAnimation(animDuration: Int): Animation {
        val outtoLeft = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,
                0.0f,
                Animation.RELATIVE_TO_PARENT,
                -1.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f,
                Animation.RELATIVE_TO_PARENT,
                0.0f
        )
        return setProperties(outtoLeft, animDuration)
    }

    private fun setProperties(animation: Animation, animDuration: Int): Animation {
        animation.duration = animDuration.toLong()
        animation.interpolator = AccelerateInterpolator()
        return animation
    }
    //Animation in tab - end


    // slide the view from below itself to the current position
    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
                0f, // fromXDelta
                0f, // toXDelta
                view.height.toFloat(), // fromYDelta
                0f
        )                // toYDelta
        animate.duration = 200
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    // slide the view from its current position to below itself
    fun slideDown(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
                0f, // fromXDelta
                0f, // toXDelta
                0f, // fromYDelta
                view.height.toFloat()
        ) // toYDelta
        animate.duration = 200
        animate.fillAfter = true
        view.startAnimation(animate)
    }


    interface CallBackAnimation {

        fun onAnimationEnd()
    }


}


