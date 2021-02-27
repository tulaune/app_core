package com.tulau.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by Tư Lầu on 2019-06-13.
 */
class MyViewPager : ViewPager {
    private var isPagingEnabled = true
    private var smoothScrollEnabled = true

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, smoothScrollEnabled)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, smoothScrollEnabled)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return this.isPagingEnabled && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        try {
            return this.isPagingEnabled && super.onInterceptTouchEvent(event)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }

        return false
    }

    fun setPagingEnabled(isPagingEnabled: Boolean) {
        this.isPagingEnabled = isPagingEnabled
    }

    fun setSmoothScrollEnabled(smoothScrollEnabled: Boolean) {
        this.smoothScrollEnabled = smoothScrollEnabled
    }
}