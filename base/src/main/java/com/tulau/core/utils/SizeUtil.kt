package com.tulau.core.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue


object SizeUtil {
    //code nay danh cho tao TAG

    @JvmStatic
    fun dpToPx(c: Context, dipValue: Float): Int {
        val metrics = c.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics).toInt()
    }

    @JvmStatic
    fun dpToPx(metrics: DisplayMetrics, dp: Int): Int {
        var px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), metrics)
        if (px < 1.0f) {
            px = 1f
        }
        return px.toInt()
    }

    @JvmStatic
    fun spToPx(context: Context, spValue: Float): Int {
        val metrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, metrics).toInt()
    }

    @JvmStatic
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }


}
