package com.tulau.core.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import com.tulau.core.R
import java.util.*

object WidgetUtil {

    private var textInf: TextInf? = null

    interface TextInf {
        fun getText(lang: String): CharSequence?
    }

    fun init(_this: TextView, typefaces: MutableMap<String, Typeface>?, context: Context, attrs: AttributeSet) {
        var mTypefaces = typefaces
        if (mTypefaces == null) {
            mTypefaces = HashMap()
        }

        // prevent exception in Android Studio / ADT interface builder
        if (_this.isInEditMode) {
            return
        }

        val array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView)
        if (array != null) {
            val typefaceAssetPath = array.getString(R.styleable.MyTextView_typeface)

            if (typefaceAssetPath != null) {
                val typeface: Typeface?

                if (mTypefaces.containsKey(typefaceAssetPath)) {
                    typeface = mTypefaces[typefaceAssetPath]
                } else {
                    val assets = context.assets
                    typeface = Typeface.createFromAsset(assets, typefaceAssetPath)
                    mTypefaces[typefaceAssetPath] = typeface!!
                }

                _this.typeface = typeface
            }

            // set language follow key
            if (textInf != null) {

                // Text
                val lang = array.getString(R.styleable.MyTextView_lang)
                if (lang != null) {
                    val `val` = textInf!!.getText(lang)
                    if (`val` != null) {
                        _this.text = `val`
                    }
                }


                // Hint
                val langHint = array.getString(R.styleable.MyTextView_langhint)
                if (langHint != null) {
                    val `val` = textInf!!.getText(langHint)
                    if (`val` != null) {
                        _this.hint = `val`
                    }
                }

            }


            if (textInf != null)
                array.recycle()
        }
    }
}
