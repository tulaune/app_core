package com.tulau.core.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.tulau.core.utils.WidgetUtil

open class MyButton : AppCompatButton {

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

//    @SuppressLint("RestrictedApi")
//    override fun setBackgroundTintList(@Nullable tint: ColorStateList) {
//        super.setSupportBackgroundTintList(tint)
//    }

    companion object {

        /*
     * Caches typefaces based on their file path and name, so that they don't have to be created
     * every time when they are referenced.
     */
        var mTypefaces: Map<String, Typeface>? = null
    }
}
