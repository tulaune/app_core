package com.tulau.core.views

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.tulau.core.utils.WidgetUtil
import java.text.DecimalFormat


open class MyTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        WidgetUtil.init(this, mTypefaces as MutableMap<String, Typeface>?, context, attrs!!)
    }


    companion object {
        /**
         * Caches typefaces based on their file path and name, so that they don't have to be created
         * every time when they are referenced.
         */
        var mTypefaces: Map<String, Typeface>? = null

        //Animate TextView to increase integer and stop at some point
        @SuppressLint("SetTextI18n")
        fun animateTextView(initialValue: Int, finalValue: Int, textview: TextView, aimDuration: Int) {
            val formatter = DecimalFormat("###,###,###.##")
            val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue)
            valueAnimator.duration = aimDuration.toLong()
            valueAnimator.addUpdateListener { itValueAnimator ->
                textview.text =
                    formatter.format(itValueAnimator.animatedValue) + "" //valueAnimator.getAnimatedValue().toString());
            }
            valueAnimator.start()
        }
    }


}
