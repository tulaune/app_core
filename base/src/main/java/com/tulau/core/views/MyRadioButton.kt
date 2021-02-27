package com.tulau.core.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.tulau.core.utils.WidgetUtil

class MyRadioButton : AppCompatRadioButton {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        WidgetUtil.init(this, mTypefaces, context, attrs!!)
    }

    companion object {
        var mTypefaces: MutableMap<String, Typeface>? = null
    }
}
