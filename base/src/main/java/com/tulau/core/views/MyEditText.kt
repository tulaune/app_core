package com.tulau.core.views

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.inputmethod.EditorInfoCompat
import androidx.core.view.inputmethod.InputConnectionCompat
import androidx.core.view.inputmethod.InputContentInfoCompat
import com.tulau.core.R
import com.tulau.core.utils.AppUtil
import com.tulau.core.utils.WidgetUtil

open class MyEditText : AppCompatEditText {

    private var mOnImeListener: PressedListener? = null
    var imgTypeString: Array<String>? = null
    private var keyBoardInputCallbackListener: KeyBoardInputCallbackListener? = null

    internal val callback: InputConnectionCompat.OnCommitContentListener =
            InputConnectionCompat.OnCommitContentListener { inputContentInfo, flags, opts ->
                // read and display inputContentInfo asynchronously
                if (Build.VERSION.SDK_INT >= 25 && flags and InputConnectionCompat.INPUT_CONTENT_GRANT_READ_URI_PERMISSION != 0) {
                    try {
                        inputContentInfo.requestPermission()
                    } catch (e: Exception) {
                        return@OnCommitContentListener false // return false if failed
                    }

                }
                var supported = false
                for (mimeType in imgTypeString!!) {
                    if (inputContentInfo.description.hasMimeType(mimeType)) {
                        supported = true
                        break
                    }
                }
                if (!supported) {
                    return@OnCommitContentListener false
                }

                if (keyBoardInputCallbackListener != null) {
                    keyBoardInputCallbackListener!!.onCommitContent(inputContentInfo, flags, opts)
                }
                true  // return true if succeeded
            }

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
        mTypefaces?.let { WidgetUtil.init(this, it, context, attrs!!) }
        imgTypeString = arrayOf("image/png", "image/gif", "image/jpeg", "image/webp")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            textCursorDrawable = ContextCompat.getDrawable(this.context, R.drawable.custom_cursor)
        }
    }


    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            if (mOnImeListener != null) {
                mOnImeListener!!.onImeBack(this)
            }
        }
        return super.onKeyPreIme(keyCode, event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
            if (mOnImeListener != null) {
                mOnImeListener!!.onImeEnter(this)
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        val ic = super.onCreateInputConnection(outAttrs)
        EditorInfoCompat.setContentMimeTypes(outAttrs, imgTypeString)
        return InputConnectionCompat.createWrapper(ic, outAttrs, callback)
    }


    fun setPressedListener(listener: PressedListener) {
        mOnImeListener = listener
    }

    interface PressedListener {
        fun onImeBack(editText: MyEditText)

        fun onImeEnter(editText: MyEditText)
    }

    override fun setError(error: CharSequence, icon: Drawable) {
        setCompoundDrawables(null, null, icon, null)
    }


    fun disableEdittext(isLock: Boolean) {
        this.isFocusable = !isLock
        this.isEnabled = !isLock
    }


    interface KeyBoardInputCallbackListener {
        fun onCommitContent(inputContentInfo: InputContentInfoCompat, flags: Int, opts: Bundle)
    }

    fun setKeyBoardInputCallbackListener(keyBoardInputCallbackListener: KeyBoardInputCallbackListener) {
        this.keyBoardInputCallbackListener = keyBoardInputCallbackListener
    }


    /**
     * dùng cho việc nhập giá tiền vào edittext format dấu chấm/phẩy tùy theo Local System của máy
     * onPendingInput trả về trạng thái nhập hoàn chỉnh ==> enable nút Send,OK
     * onInputed  trả về giá trị đã nhập vào edit với định dạng String KHÔNG  có dấu chấm/phẩy
     */
    fun inputCurrencyType(inputCallback: InputingCallBack) {
        var valueOutPut = ""
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                inputCallback?.onPendingInput(true)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                removeTextChangedListener(this)

                try {
                    var originalString = s.toString()

                    if (originalString.contains(",")) {
                        originalString = originalString.replace(",".toRegex(), "")
                    }
                    valueOutPut = originalString

                    //setting text after format to EditText
                    if (originalString.isNotEmpty()) setText(AppUtil.currencyFormatWithComma(originalString.toDouble()))

                    //move con trỏ về cuối dòng
                    setSelection(text?.length ?: 0)

                    inputCallback?.onPendingInput(valueOutPut.isNotEmpty())
                    inputCallback?.onInputed(valueOutPut)


                } catch (nfe: NumberFormatException) {
                    nfe.printStackTrace()
                }

                addTextChangedListener(this)
            }
        })
    }

    companion object {

        /*
     * Caches typefaces based on their file path and name, so that they don't have to be created
     * every time when they are referenced.
     */
        var mTypefaces: MutableMap<String, Typeface>? = null

        interface InputingCallBack {
            fun onPendingInput(isClickEnable: Boolean)
            fun onInputed(valueOutPut: String)
        }
    }

}


