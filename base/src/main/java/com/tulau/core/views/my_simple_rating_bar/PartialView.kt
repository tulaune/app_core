package com.tulau.core.views.my_simple_rating_bar

import android.content.Context
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout

/**
 * Created by Tư Lầu on 2/28/18.
 */
class PartialView : RelativeLayout {
    private var mFilledView: ImageView? = null
    private var mEmptyView: ImageView? = null
    private var mStarWidth = 0
    private var mStarHeight = 0

    constructor(context: Context?, starWidth: Int, startHeight: Int) : super(context) {
        mStarWidth = starWidth
        mStarHeight = startHeight
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        val params = ViewGroup.LayoutParams(
                if (mStarWidth == 0) LayoutParams.WRAP_CONTENT else mStarWidth,
                if (mStarHeight == 0) LayoutParams.WRAP_CONTENT else mStarHeight)
        mFilledView = ImageView(context)
        mFilledView!!.scaleType = ImageView.ScaleType.CENTER_CROP
        addView(mFilledView, params)
        mEmptyView = ImageView(context)
        mEmptyView!!.scaleType = ImageView.ScaleType.CENTER_CROP
        addView(mEmptyView, params)
        setEmpty()
    }

    fun setFilledDrawable(drawable: Drawable?) {
        if (drawable!!.constantState == null) {
            return
        }
        val clipDrawable = ClipDrawable(drawable.constantState!!.newDrawable(), Gravity.START, ClipDrawable.HORIZONTAL)
        mFilledView!!.setImageDrawable(clipDrawable)
    }

    fun setEmptyDrawable(drawable: Drawable?) {
        if (drawable!!.constantState == null) {
            return
        }
        val clipDrawable = ClipDrawable(drawable.constantState!!.newDrawable(), Gravity.END, ClipDrawable.HORIZONTAL)
        mEmptyView!!.setImageDrawable(clipDrawable)
    }

    fun setFilled() {
        mFilledView!!.setImageLevel(10000)
        mEmptyView!!.setImageLevel(0)
    }

    fun setPartialFilled(rating: Float) {
        val percentage = rating % 1
        var level = (10000 * percentage).toInt()
        level = if (level == 0) 10000 else level
        mFilledView!!.setImageLevel(level)
        mEmptyView!!.setImageLevel(10000 - level)
    }

    fun setEmpty() {
        mFilledView!!.setImageLevel(0)
        mEmptyView!!.setImageLevel(10000)
    }
}