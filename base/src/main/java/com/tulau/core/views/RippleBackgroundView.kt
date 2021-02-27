package com.tulau.core.views

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.tulau.core.R
import java.util.*

/**
 * Created by fyu on 11/3/14.
 */
class RippleBackgroundView : RelativeLayout {
    private var rippleColor = 0
    private var rippleStrokeWidth = 0f
    private var rippleRadius = 0f
    private var rippleDurationTime = 0
    private var rippleAmount = 0
    private var rippleDelay = 0
    private var rippleScale = 0f
    private var rippleType = 0
    private var paint: Paint? = null
    var isRippleAnimationRunning = false
        private set
    private var animatorSet: AnimatorSet? = null
    private var animatorList: ArrayList<Animator>? = null
    private var rippleParams: LayoutParams? = null
    private val rippleViewList = ArrayList<RippleView>()

    constructor(context: Context?) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (isInEditMode) return
        requireNotNull(attrs) { "Attributes should be provided to this view," }
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleBackground)
        rippleColor = typedArray.getColor(R.styleable.RippleBackground_rb_color,
                ContextCompat.getColor(context, R.color.rippelColorCall))
        rippleStrokeWidth = typedArray.getDimension(R.styleable.RippleBackground_rb_strokeWidth,
                resources.getDimension(R.dimen.rippleStrokeWidth))
        rippleRadius = typedArray.getDimension(R.styleable.RippleBackground_rb_radius,
                resources.getDimension(R.dimen.rippleRadius))
        rippleDurationTime = typedArray.getInt(R.styleable.RippleBackground_rb_duration, DEFAULT_DURATION_TIME)
        rippleAmount = typedArray.getInt(R.styleable.RippleBackground_rb_rippleAmount, DEFAULT_RIPPLE_COUNT)
        rippleScale = typedArray.getFloat(R.styleable.RippleBackground_rb_scale, DEFAULT_SCALE)
        rippleType = typedArray.getInt(R.styleable.RippleBackground_rb_type, DEFAULT_FILL_TYPE)
        typedArray.recycle()
        rippleDelay = rippleDurationTime / rippleAmount
        paint = Paint()
        paint!!.isAntiAlias = true
        if (rippleType == DEFAULT_FILL_TYPE) {
            rippleStrokeWidth = 0f
            paint!!.style = Paint.Style.FILL
        } else paint!!.style = Paint.Style.STROKE
        paint!!.color = rippleColor
        rippleParams = LayoutParams((2 * (rippleRadius + 2)).toInt(),
                (2 * (rippleRadius + 2)).toInt())
        rippleParams!!.addRule(CENTER_IN_PARENT, TRUE)
        animatorSet = AnimatorSet()
        animatorSet!!.interpolator = AccelerateDecelerateInterpolator()
        animatorList = ArrayList()
        for (i in 0 until rippleAmount) {
            val rippleView0 = RippleView(getContext(), 0, 0f, ContextCompat.getColor(context, R.color.pink_circle_second))
            addView(rippleView0, rippleParams)
            rippleViewList.add(rippleView0)
            val scaleXAnimator0 = ObjectAnimator.ofFloat(rippleView0, "ScaleX", 1.0f, rippleScale)
            scaleXAnimator0.repeatCount = ObjectAnimator.INFINITE
            scaleXAnimator0.repeatMode = ObjectAnimator.RESTART
            scaleXAnimator0.startDelay = (i * rippleDelay).toLong()
            scaleXAnimator0.duration = rippleDurationTime.toLong()
            animatorList!!.add(scaleXAnimator0)
            val scaleYAnimator0 = ObjectAnimator.ofFloat(rippleView0, "ScaleY", 1.0f, rippleScale)
            scaleYAnimator0.repeatCount = ObjectAnimator.INFINITE
            scaleYAnimator0.repeatMode = ObjectAnimator.RESTART
            scaleYAnimator0.startDelay = (i * rippleDelay).toLong()
            scaleYAnimator0.duration = rippleDurationTime.toLong()
            animatorList!!.add(scaleYAnimator0)
            val alphaAnimator0 = ObjectAnimator.ofFloat(rippleView0, "Alpha", 1.5f, 0f)
            alphaAnimator0.repeatCount = ObjectAnimator.INFINITE
            alphaAnimator0.repeatMode = ObjectAnimator.RESTART
            alphaAnimator0.startDelay = (i * rippleDelay).toLong()
            alphaAnimator0.duration = rippleDurationTime.toLong()
            animatorList!!.add(alphaAnimator0)
            val rippleView1 = RippleView(getContext(), 0, 0f, ContextCompat.getColor(context, R.color.pink_circle_second))
            addView(rippleView1, rippleParams)
            rippleViewList.add(rippleView1)
            val scaleXAnimator1 = ObjectAnimator.ofFloat(rippleView1, "ScaleX", 1.0f, rippleScale)
            scaleXAnimator1.repeatCount = ObjectAnimator.INFINITE
            scaleXAnimator1.repeatMode = ObjectAnimator.RESTART
            scaleXAnimator1.startDelay = i * rippleDelay + 100.toLong()
            scaleXAnimator1.duration = rippleDurationTime.toLong()
            animatorList!!.add(scaleXAnimator1)
            val scaleYAnimator1 = ObjectAnimator.ofFloat(rippleView1, "ScaleY", 1.0f, rippleScale)
            scaleYAnimator1.repeatCount = ObjectAnimator.INFINITE
            scaleYAnimator1.repeatMode = ObjectAnimator.RESTART
            scaleYAnimator1.startDelay = i * rippleDelay + 100.toLong()
            scaleYAnimator1.duration = rippleDurationTime.toLong()
            animatorList!!.add(scaleYAnimator1)
            val alphaAnimator1 = ObjectAnimator.ofFloat(rippleView1, "Alpha", 1.5f, 0f)
            alphaAnimator1.repeatCount = ObjectAnimator.INFINITE
            alphaAnimator1.repeatMode = ObjectAnimator.RESTART
            alphaAnimator1.startDelay = i * rippleDelay + 100.toLong()
            alphaAnimator1.duration = rippleDurationTime.toLong()
            animatorList!!.add(alphaAnimator1)
            val rippleView2 = RippleView(getContext(), 0, 0f, ContextCompat.getColor(context, R.color.pink_circle_second))
            addView(rippleView2, rippleParams)
            rippleViewList.add(rippleView2)
            val scaleXAnimator2 = ObjectAnimator.ofFloat(rippleView2, "ScaleX", 1.0f, rippleScale)
            scaleXAnimator2.repeatCount = ObjectAnimator.INFINITE
            scaleXAnimator2.repeatMode = ObjectAnimator.RESTART
            scaleXAnimator2.startDelay = i * rippleDelay + 500.toLong()
            scaleXAnimator2.duration = rippleDurationTime.toLong()
            animatorList!!.add(scaleXAnimator2)
            val scaleYAnimator2 = ObjectAnimator.ofFloat(rippleView2, "ScaleY", 1.0f, rippleScale)
            scaleYAnimator2.repeatCount = ObjectAnimator.INFINITE
            scaleYAnimator2.repeatMode = ObjectAnimator.RESTART
            scaleYAnimator2.startDelay = i * rippleDelay + 500.toLong()
            scaleYAnimator2.duration = rippleDurationTime.toLong()
            animatorList!!.add(scaleYAnimator2)
            val alphaAnimator2 = ObjectAnimator.ofFloat(rippleView2, "Alpha", 1.5f, 0f)
            alphaAnimator2.repeatCount = ObjectAnimator.INFINITE
            alphaAnimator2.repeatMode = ObjectAnimator.RESTART
            alphaAnimator2.startDelay = i * rippleDelay + 500.toLong()
            alphaAnimator2.duration = rippleDurationTime.toLong()
            animatorList!!.add(alphaAnimator2)
            val rippleView3 = RippleView(getContext(), 0, 0f, ContextCompat.getColor(context, R.color.pink_circle_second))
            addView(rippleView3, rippleParams)
            rippleViewList.add(rippleView3)
            val scaleXAnimator3 = ObjectAnimator.ofFloat(rippleView3, "ScaleX", 1.0f, rippleScale)
            scaleXAnimator3.repeatCount = ObjectAnimator.INFINITE
            scaleXAnimator3.repeatMode = ObjectAnimator.RESTART
            scaleXAnimator3.startDelay = i * rippleDelay + 600.toLong()
            scaleXAnimator3.duration = rippleDurationTime.toLong()
            animatorList!!.add(scaleXAnimator3)
            val scaleYAnimator3 = ObjectAnimator.ofFloat(rippleView3, "ScaleY", 1.0f, rippleScale)
            scaleYAnimator3.repeatCount = ObjectAnimator.INFINITE
            scaleYAnimator3.repeatMode = ObjectAnimator.RESTART
            scaleYAnimator3.startDelay = i * rippleDelay + 600.toLong()
            scaleYAnimator3.duration = rippleDurationTime.toLong()
            animatorList!!.add(scaleYAnimator3)
            val alphaAnimator3 = ObjectAnimator.ofFloat(rippleView3, "Alpha", 1.5f, 0f)
            alphaAnimator3.repeatCount = ObjectAnimator.INFINITE
            alphaAnimator3.repeatMode = ObjectAnimator.RESTART
            alphaAnimator3.startDelay = i * rippleDelay + 600.toLong()
            alphaAnimator3.duration = rippleDurationTime.toLong()
            animatorList!!.add(alphaAnimator3)
        }
        animatorSet!!.playTogether(animatorList)
    }

    private inner class RippleView : View {
        var myPaint: Paint? = null
        var strokewidth = 0f
        var type = 0
        var color = 0

        constructor(context: Context?) : super(context) {
            this.visibility = INVISIBLE
        }

        constructor(context: Context?, style: Int, stroke: Float, color: Int) : super(context) {
            this.visibility = INVISIBLE
            myPaint = Paint()
            myPaint!!.isAntiAlias = true
            type = type
            this.color = color
            strokewidth = 0f
            myPaint!!.style = Paint.Style.FILL
            //            myPaint.setColor(color);
        }

        override fun onDraw(canvas: Canvas) {
            val radius = Math.min(width, height) / 2
            if (type == 0) {
                myPaint!!.shader = LinearGradient(width.toFloat(), 0f, 0f, 0f, ContextCompat.getColor(context, R.color.main_color_right), ContextCompat.getColor(context, R.color.main_color_left), Shader.TileMode.CLAMP)
            } else {
                myPaint!!.color = color
            }
            canvas.drawCircle(radius.toFloat(), radius.toFloat(), radius.toFloat(), myPaint!!)
        }
    }

    fun startRippleAnimation() {
        if (!isRippleAnimationRunning) {
            for (rippleView in rippleViewList) {
                rippleView.visibility = View.VISIBLE
            }
            animatorSet!!.start()
            isRippleAnimationRunning = true
        }
    }

    fun stopRippleAnimation() {
        if (isRippleAnimationRunning) {
            animatorSet!!.end()
            isRippleAnimationRunning = false
        }
    }

    companion object {
        private const val DEFAULT_RIPPLE_COUNT = 6
        private const val DEFAULT_DURATION_TIME = 3000
        private const val DEFAULT_SCALE = 6.0f
        private const val DEFAULT_FILL_TYPE = 0
    }
}