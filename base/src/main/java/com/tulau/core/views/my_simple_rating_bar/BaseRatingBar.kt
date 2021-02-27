package com.tulau.core.views.my_simple_rating_bar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.tulau.core.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

/**
 * Created by Tư Lầu on 2/28/18.
 */
open class BaseRatingBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr), SimpleRatingBar {
    interface OnRatingChangeListener {
        fun onRatingChange(ratingBar: BaseRatingBar?, rating: Float)
    }

    private var mDecimalFormat: DecimalFormat? = null
    private var mNumStars = 0
    private var mPadding = 20
    private var mStarWidth = 0
    private var mStarHeight = 0
    private var mRating = -1f
    var stepSize = 1f
    private var mPreviousRating = 0f
    var isIndicator = false
    var isScrollable = true
    private var mIsClickable = true
    var isClearRatingEnabled = true
    private var mStartX = 0f
    private var mStartY = 0f
    private var mEmptyDrawable: Drawable? = null
    private var mFilledDrawable: Drawable? = null
    private var mOnRatingChangeListener: OnRatingChangeListener? = null
    protected var mPartialViews: MutableList<PartialView>? = null
    private fun initParamsValue(typedArray: TypedArray, context: Context) {
        mNumStars = typedArray.getInt(R.styleable.BaseRatingBar_srb_numStars, mNumStars)
        mPadding = typedArray.getDimensionPixelSize(R.styleable.BaseRatingBar_srb_starPadding, mPadding)
        mStarWidth = typedArray.getDimensionPixelSize(R.styleable.BaseRatingBar_srb_starWidth, 0)
        mStarHeight = typedArray.getDimensionPixelSize(R.styleable.BaseRatingBar_srb_starHeight, 0)
        stepSize = typedArray.getFloat(R.styleable.BaseRatingBar_srb_stepSize, stepSize)
        mEmptyDrawable = if (typedArray.hasValue(R.styleable.BaseRatingBar_srb_drawableEmpty)) ContextCompat.getDrawable(context, typedArray.getResourceId(R.styleable.BaseRatingBar_srb_drawableEmpty, View.NO_ID)) else null
        mFilledDrawable = if (typedArray.hasValue(R.styleable.BaseRatingBar_srb_drawableFilled)) ContextCompat.getDrawable(context, typedArray.getResourceId(R.styleable.BaseRatingBar_srb_drawableFilled, View.NO_ID)) else null
        isIndicator = typedArray.getBoolean(R.styleable.BaseRatingBar_srb_isIndicator, isIndicator)
        isScrollable = typedArray.getBoolean(R.styleable.BaseRatingBar_srb_scrollable, isScrollable)
        mIsClickable = typedArray.getBoolean(R.styleable.BaseRatingBar_srb_clickable, mIsClickable)
        isClearRatingEnabled = typedArray.getBoolean(R.styleable.BaseRatingBar_srb_clearRatingEnabled, isClearRatingEnabled)
        typedArray.recycle()
    }

    private fun verifyParamsValue() {
        if (mNumStars <= 0) {
            mNumStars = 5
        }
        if (mPadding < 0) {
            mPadding = 0
        }
        if (mEmptyDrawable == null) {
            mEmptyDrawable = ContextCompat.getDrawable(context, R.drawable.star_empty)
        }
        if (mFilledDrawable == null) {
            mFilledDrawable = ContextCompat.getDrawable(context, R.drawable.star_full_yellow)
        }
        if (stepSize > 1.0f) {
            stepSize = 1.0f
        } else if (stepSize < 0.1f) {
            stepSize = 0.1f
        }
    }

    private fun initRatingView() {
        mPartialViews = ArrayList()
        for (i in 1..mNumStars) {
            val partialView = getPartialView(i, mFilledDrawable, mEmptyDrawable)
            addView(partialView)
            mPartialViews?.add(partialView)
        }
    }

    private fun getPartialView(ratingViewId: Int, filledDrawable: Drawable?, emptyDrawable: Drawable?): PartialView {
        val partialView = PartialView(context, mStarWidth, mStarHeight)
        partialView.tag = ratingViewId
        partialView.setPadding(mPadding, mPadding, mPadding, mPadding)
        partialView.setFilledDrawable(filledDrawable)
        partialView.setEmptyDrawable(emptyDrawable)
        return partialView
    }

    /**
     * Retain this method to let other RatingBar can custom their decrease animation.
     */
    protected open fun emptyRatingBar() {
        fillRatingBar(0f)
    }

    /**
     * Use {maxIntOfRating} because if the rating is 3.5
     * the view which id is 3 also need to be filled.
     */
    protected open fun fillRatingBar(rating: Float) {
        for (partialView in mPartialViews!!) {
            val ratingViewId = partialView.tag as Int
            val maxIntOfRating = Math.ceil(rating.toDouble())
            if (ratingViewId > maxIntOfRating) {
                partialView.setEmpty()
                continue
            }
            if (ratingViewId.toDouble() == maxIntOfRating) {
                partialView.setPartialFilled(rating)
            } else {
                partialView.setFilled()
            }
        }
    }

    override var numStars: Int
        get() = mNumStars
        set(numStars) {
            if (numStars <= 0) {
                return
            }
            mPartialViews!!.clear()
            removeAllViews()
            mNumStars = numStars
            initRatingView()
        }

    override var rating: Float
        get() = mRating
        set(rating) {
            var rating = rating
            if (rating > mNumStars) {
                rating = mNumStars.toFloat()
            }
            if (rating < 0) {
                rating = 0f
            }
            if (mRating == rating) {
                return
            }
            mRating = rating
            mOnRatingChangeListener?.onRatingChange(this, mRating)
            fillRatingBar(rating)
        }

    override var starPadding: Int
        get() = mPadding
        set(ratingPadding) {
            if (ratingPadding < 0) {
                return
            }
            mPadding = ratingPadding
            for (partialView in mPartialViews!!) {
                partialView.setPadding(mPadding, mPadding, mPadding, mPadding)
            }
        }

    override fun setEmptyDrawableRes(@DrawableRes res: Int) {
        setEmptyDrawable(ContextCompat.getDrawable(context, res))
    }

    override fun setFilledDrawableRes(@DrawableRes res: Int) {
        setFilledDrawable(ContextCompat.getDrawable(context, res))
    }

    override fun setEmptyDrawable(drawable: Drawable?) {
        mEmptyDrawable = drawable
        for (partialView in mPartialViews!!) {
            partialView.setEmptyDrawable(drawable)
        }
    }

    override fun setFilledDrawable(drawable: Drawable?) {
        mFilledDrawable = drawable
        for (partialView in mPartialViews!!) {
            partialView.setFilledDrawable(drawable)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isIndicator) {
            return false
        }
        val eventX = event.x
        val eventY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartX = eventX
                mStartY = eventY
                mPreviousRating = mRating
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isScrollable) {
                    return false
                }
                handleMoveEvent(eventX)
            }
            MotionEvent.ACTION_UP -> {
                if (!isClickEvent(mStartX, mStartY, event) || !isClickable) {
                    return false
                }
                handleClickEvent(eventX)
            }
        }
        parent.requestDisallowInterceptTouchEvent(true)
        return true
    }

    private fun handleMoveEvent(eventX: Float) {
        for (partialView in mPartialViews!!) {
            if (eventX < partialView.width / 10f) {
                rating = 0f
                return
            }
            if (!isPositionInRatingView(eventX, partialView)) {
                continue
            }
            val ratingCal = calculateRating(eventX, partialView)
            if (mRating != ratingCal) {
                rating = ratingCal
            }
        }
    }

    private fun calculateRating(eventX: Float, partialView: PartialView): Float {
        val decimalFormat = decimalFormat
        val ratioOfView = decimalFormat.format((eventX - partialView.left) / partialView.width.toDouble()).toFloat()
        val steps = Math.round(ratioOfView / stepSize) * stepSize
        return decimalFormat.format(partialView.tag as Int - (1 - steps).toDouble()).toFloat()
    }

    private fun handleClickEvent(eventX: Float) {
        for (partialView in mPartialViews!!) {
            if (!isPositionInRatingView(eventX, partialView)) {
                continue
            }
            var rating = if (stepSize == 1f) (partialView.tag as Int).toFloat() else calculateRating(eventX, partialView)
            rating = if (mPreviousRating == rating && isClearRatingEnabled) {
                0f
            } else {
                rating
            }
            break
        }
    }

    private fun isPositionInRatingView(eventX: Float, ratingView: View): Boolean {
        return eventX > ratingView.left && eventX < ratingView.right
    }

    private fun isClickEvent(startX: Float, startY: Float, event: MotionEvent): Boolean {
        val duration = event.eventTime - event.downTime.toFloat()
        if (duration > MAX_CLICK_DURATION) {
            return false
        }
        val differenceX = Math.abs(startX - event.x)
        val differenceY = Math.abs(startY - event.y)
        return !(differenceX > MAX_CLICK_DISTANCE || differenceY > MAX_CLICK_DISTANCE)
    }

    fun setOnRatingChangeListener(onRatingChangeListener: OnRatingChangeListener?) {
        mOnRatingChangeListener = onRatingChangeListener
    }

    override fun isClickable(): Boolean {
        return mIsClickable
    }

    override fun setClickable(clickable: Boolean) {
        mIsClickable = clickable
    }

    val decimalFormat: DecimalFormat
        get() {
            if (mDecimalFormat == null) {
                val symbols = DecimalFormatSymbols()
                symbols.decimalSeparator = '.'
                mDecimalFormat = DecimalFormat("#.##", symbols)
            }
            return mDecimalFormat!!
        }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val ss = SavedState(superState)
        ss.rating = mRating
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val ss = state as SavedState
        super.onRestoreInstanceState(ss.superState)
        rating = ss.rating
    }

    companion object {
        const val TAG = "SimpleRatingBar"
        private const val MAX_CLICK_DISTANCE = 5
        private const val MAX_CLICK_DURATION = 200
    }

    /**
     * @param context      context
     * @param attrs        attributes from XML => app:mainText="mainText"
     * @param defStyleAttr attributes from default style (Application theme or activity theme)
     */
/* Call by xml layout */
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseRatingBar)
        var rating = typedArray.getFloat(R.styleable.BaseRatingBar_srb_rating, 0f)
        initParamsValue(typedArray, context)
        verifyParamsValue()
        initRatingView()
        rating = rating
    }
}