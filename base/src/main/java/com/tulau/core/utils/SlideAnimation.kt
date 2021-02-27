package com.tulau.core.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class SlideAnimation : Animation {
    var mFromWidth = 0
    var mFromHeight = 0
    var mToWidth = 0
    var mToHeight = 0
    var changeWidth = false
    var mView: View? = null

    constructor() {}

    constructor(fromHeight: Int, toHeight: Int) {
        mFromHeight = fromHeight
        mToHeight = toHeight
    }

    constructor(view: View?, fromHeight: Int, toHeight: Int) {
        mView = view
        mFromHeight = fromHeight
        mToHeight = toHeight
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        val newHeight: Int
        val newWidth: Int
        if (mView!!.height != mToHeight) {
            newHeight = (mFromHeight + (mToHeight - mFromHeight) * interpolatedTime).toInt()
            mView!!.layoutParams.height = newHeight
        }
        if (changeWidth && mView!!.width != mToWidth) {
            newWidth = (mFromWidth + (mToWidth - mFromWidth) * interpolatedTime).toInt()
            mView!!.layoutParams.width = newWidth
        }
        mView!!.requestLayout()
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
    }

    override fun willChangeBounds(): Boolean {
        return true
    }

    fun setmView(mView: View?) {
        this.mView = mView
    }

    fun setmFromHeight(mFromHeight: Int) {
        this.mFromHeight = mFromHeight
    }

    fun setmToHeight(mToHeight: Int) {
        this.mToHeight = mToHeight
    }

    fun setmFromWidth(mFromWidth: Int) {
        this.mFromWidth = mFromWidth
    }

    fun setmToWidth(mToWidth: Int) {
        this.mToWidth = mToWidth
    }
}