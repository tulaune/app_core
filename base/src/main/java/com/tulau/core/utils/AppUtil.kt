package com.tulau.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.*
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.MetricAffectingSpan
import android.util.TypedValue
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.tulau.core.Inf.KeyboardToggleInf
import com.tulau.core.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.experimental.and
import kotlin.math.round


object AppUtil {

    fun initConstants() {
        Constants.LINK_SERVICE_V2 = "https://webservice.piepjobs.com/"
        Constants.LINK_SERVICE_PIEPME_CA = "https://webservice.piepme.com/"
        Constants.LINK_SERVICE_LOGIN = "https://webservice.piepjobs.com/"
        Constants.LINK_SERVICE_LOGIN_VN = "https://webservice.piepjobs.com/"
        Constants.DOMAIN_ATTACH_POST_DATA = "https://media2.piepme.com/" /* "https://upload.piepme.com/"*/
        Constants.DOMAIN_GOOGLE = "https://maps.googleapis.com/"

        Constants.AES_KEY = String(byteArrayOf(102, 106, 107, 51, 57, 51, 115, 104, 115, 51, 50, 51, 102, 104, 50, 106))
        Constants.AES_IV = String(byteArrayOf(122, 120, 99, 109, 106, 97, 115, 100, 104, 107, 115, 97, 104, 100, 51, 51))

        Constants.DOMAIN_LINK_SOCKET = "piepme.com"

        //Constants.LINK_SOCKET = String(byteArrayOf(104, 116, 116, 112, 115, 58, 47, 47, 110, 111, 100, 101, 105, 110, 102, 111, 46, 112, 105, 101, 112, 109, 101, 46, 99, 111, 109, 47))
        Constants.LINK_SOCKET = "https://nodeinfo.piepme.com/"
    }


    fun fixedLengthString(strContent: String, length: Int): String {
        var string = strContent
        string = String.format("%1$" + length + "s", string).replace(' ', '0')
        return string.substring(0, length)
    }

    fun formatString(str: String): String {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
    }

    @Throws(NoSuchAlgorithmException::class)
    fun getJsonObjectPostHasTokenV3(jsonObject: JSONObject): JSONObject {
        val manufacturer = Build.MANUFACTURER
        try {
            jsonObject.put(Constants.KEY_TOKEN, Constants.KEY_TOKEN_VALUE)
            jsonObject.put(Constants.KEY_TOKEN_VERSION, Constants.KEY_TOKEN_VERSION_VALUE)
            jsonObject.put("MODEL", manufacturer.toLowerCase(Locale.ENGLISH))
            jsonObject.put("OS", Build.VERSION.SDK_INT)
            jsonObject.put("SRC", Constants.SOURCE_DEVICE)
            jsonObject.put("VERSION", Constants.versionCode)
        } catch (e: JSONException) {
            e.printStackTrace()
        }


        val tmpString = StringBuilder()

        LogUtil.requestParam(">>>param_for_request>>>", jsonObject.toString())
        val jsonData = sortJsonObjectV3(jsonObject, tmpString)
        LogUtil.e(">>>param_for_request_encoded>>>", MD5Encoder(jsonData))
        //LogUtil.e(">>>Request_Param>>>", MD5Encoder(jsonData) + "-----" + jsonData)

        jsonObject.put("sign", MD5Encoder(jsonData))
        return jsonObject
    }


    @Throws(NoSuchAlgorithmException::class)
    fun createTokenPostNewV3(jsonObject: JSONObject): String {
        val manufacturer = Build.MANUFACTURER
        try {
            jsonObject.put(Constants.KEY_TOKEN, Constants.KEY_TOKEN_VALUE)
            jsonObject.put(Constants.KEY_TOKEN_VERSION, Constants.KEY_TOKEN_VERSION_VALUE)
            jsonObject.put("MODEL", manufacturer.toLowerCase(Locale.ENGLISH))
            jsonObject.put("OS", Build.VERSION.SDK_INT)
            jsonObject.put("SRC", Constants.SOURCE_DEVICE)
            jsonObject.put("VERSION", Constants.versionCode)
        } catch (e: JSONException) {
            e.printStackTrace()
        }


        val tmpString = StringBuilder()

        LogUtil.requestParam(">>>param_for_request>>>", jsonObject.toString())
        val jsonData = sortJsonObjectV3(jsonObject, tmpString)
        LogUtil.e(">>>param_for_request_encoded>>>", MD5Encoder(jsonData))
        //LogUtil.e(">>>Request_Param>>> ", MD5Encoder(jsonData) + "-----" + jsonData)
        return MD5Encoder(jsonData)
    }


    @Throws(NoSuchAlgorithmException::class)
    fun createPIEPMETokenPostNewV3(jsonObject: JSONObject): String {
        val manufacturer = Build.MANUFACTURER
        try {
            jsonObject.put(Constants.KEY_TOKEN, "Piepme2017")
            jsonObject.put(Constants.KEY_TOKEN_VERSION, Constants.KEY_TOKEN_VERSION_VALUE)
            jsonObject.put("MODEL", manufacturer.toLowerCase(Locale.ENGLISH))
            jsonObject.put("OS", Build.VERSION.SDK_INT)
            jsonObject.put("SRC", Constants.SOURCE_DEVICE)
            jsonObject.put("VERSION", Constants.versionCode)
        } catch (e: JSONException) {
            e.printStackTrace()
        }


        val tmpString = StringBuilder()

        LogUtil.requestParam(">>>param_for_request>>>", jsonObject.toString())
        val jsonData = sortJsonObjectV3(jsonObject, tmpString)
        LogUtil.e(">>>param_for_request_encoded>>>", MD5Encoder(jsonData))
        //LogUtil.e(">>>Request_Param>>> ", MD5Encoder(jsonData) + "-----" + jsonData)
        return MD5Encoder(jsonData)
    }


    fun sortJsonObjectV3(jsonObject: JSONObject, tmpString: StringBuilder): String {
        val arrData = sortJsonArray(jsonObject.names())
        for (i in 0 until arrData.length()) {
            try {
                if (jsonObject.get(arrData.getString(i)) is JSONObject) {
                    tmpString.append(arrData.getString(i))
                    tmpString.append("=[")
                    tmpString.append(
                            sortJsonObjectV3(
                                    jsonObject.get(arrData.getString(i)) as JSONObject,
                                    StringBuilder()
                            )
                    )
                    tmpString.append("]")
                } else if (jsonObject.get(arrData.getString(i)) is JSONArray) {
                    tmpString.append(arrData.getString(i))
                    tmpString.append("=[")
                    tmpString.append(
                            sortJsonArrayV3(
                                    jsonObject.get(arrData.getString(i)) as JSONArray,
                                    StringBuilder()
                            )
                    )
                    tmpString.append("]")
                } else {
                    tmpString.append(arrData.getString(i))
                    tmpString.append("=")
                    if (jsonObject.get(arrData.getString(i)) is Double) {
                        tmpString.append(numberToString(jsonObject.get(arrData.getString(i)) as Double).replace("[^a-zA-Z0-9]".toRegex(), ""))
                    } else if (jsonObject.get(arrData.getString(i)) is Number) {
                        tmpString.append(numberToString(jsonObject.get(arrData.getString(i)) as Number).replace("[^a-zA-Z0-9]".toRegex(), ""))
                    } else {
                        val txt = jsonObject.get(arrData.getString(i)).toString().replace("[^a-zA-Z0-9]".toRegex(), "")
                        tmpString.append(txt)
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            if (i + 1 < arrData.length()) {
                tmpString.append("&")
            }

            if (i + 1 == arrData.length()) {
                return tmpString.toString()
            }
        }
        return ""
    }

    fun sortJsonArray(array: JSONArray?): JSONArray {
        val jsons = ArrayList<String>()
        try {
            if (array != null) {
                for (i in 0 until array.length()) {
                    jsons.add(array.getString(i))

                }
                Collections.sort(jsons)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return JSONArray(jsons)

    }

    private fun sortJsonArrayV3(jsonArray: JSONArray, tmpString: StringBuilder): String {
        for (i in 0 until jsonArray.length()) {
            try {
                if (jsonArray.get(i) is JSONObject) {
                    tmpString.append(i)
                    tmpString.append("=[")
                    tmpString.append(
                            sortJsonObjectV3(
                                    jsonArray.get(i) as JSONObject,
                                    StringBuilder()
                            )
                    )
                    tmpString.append("]")
                } else if (jsonArray.get(i) is JSONArray) {
                    tmpString.append(i)
                    tmpString.append("=[")
                    tmpString.append(sortJsonArrayV3(jsonArray.get(i) as JSONArray, StringBuilder()))
                    tmpString.append("]")
                } else {
                    tmpString.append(i)
                    tmpString.append("=")
                    if (jsonArray.get(i) is Number) {
                        tmpString.append(numberToString(jsonArray.get(i) as Number).replace("[^a-zA-Z0-9]".toRegex(), ""))
                    } else {
                        val txt = jsonArray.get(i).toString().replace("[^a-zA-Z0-9]".toRegex(), "")
                        tmpString.append(txt)
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            if (i + 1 < jsonArray.length()) {
                tmpString.append("&")
            }

            if (i + 1 == jsonArray.length()) {
                return tmpString.toString()
            }
        }
        return ""
    }

    fun numberToString(n: Number): String {
        var str = n.toString()
        if (str.indexOf('.') > 0) {
            if (str.indexOf('e') < 0 && str.indexOf('E') < 0) {
                while (str.endsWith("0")) {
                    str = str.substring(0, str.length - 1)
                }
                if (str.endsWith(".")) {
                    str = str.substring(0, str.length - 1)
                }
            } else {
                //val f = DecimalFormat("###")
                str = formatStringDouble(str)
            }
        }
        return str
    }

    fun formatStringDouble(number: String): String {
        if (number.length > 0) {
            val f = DecimalFormat("###")
            return f.format(java.lang.Double.parseDouble(number))
        }

        return ""
    }

    @Throws(NoSuchAlgorithmException::class)
    fun MD5Encoder(keyEncode: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(keyEncode.toByteArray())

        val byteData = md.digest()

        //convert the byte to hex format method 1
        val sb = StringBuffer()
        for (i in byteData.indices) {
            sb.append(Integer.toString((byteData[i] and 0xff.toByte()) + 0x100, 16).substring(1))
        }

        //convert the byte to hex format method 2
        val hexString = StringBuffer()
        for (i in byteData.indices) {
            val hex = Integer.toHexString(0xff and byteData[i].toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        return hexString.toString()
    }

    fun addParamDefault(obj: JSONObject): JSONObject {
        val manufacturer = Build.MANUFACTURER
        try {
            obj.put("MODEL", manufacturer.toLowerCase(Locale.ENGLISH))
            obj.put("OS", Build.VERSION.SDK_INT)
            obj.put("SRC", Constants.SOURCE_DEVICE)
            obj.put("VERSION", /*BuildConfig.VERSION_CODE*/Constants.versionCode)
            obj.put("v", Constants.KEY_TOKEN_VERSION_VALUE)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return obj

    }


    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInputFromWindow(
                view.applicationWindowToken,
                InputMethodManager.SHOW_FORCED, 0)
//        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
//        view.requestFocus()
    }

    fun showKeyboardAfterDialogDismiss(context: Context) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }


    fun keyboardShowing(view: View): Boolean {
        var keypadHeight = 0
        var screenHeight = 0
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            view.getWindowVisibleDisplayFrame(r)
            screenHeight = view.rootView.height

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            keypadHeight = screenHeight - r.bottom

        }
        // 0.15 ratio is perhaps enough to determine keypad height.
        return (keypadHeight > screenHeight * 0.15)
    }


    fun detectKeyboardToggle(view: View, callBack: KeyboardToggleInf?) {
        view.viewTreeObserver.addOnGlobalLayoutListener {

            val r = Rect()
            view.getWindowVisibleDisplayFrame(r)
            val screenHeight = view.rootView.height

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            val keypadHeight = screenHeight - r.bottom

            if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                // keyboard is opened
                callBack?.opened(keypadHeight)
            } else {
                // keyboard is closed
                callBack?.closed()
            }
        }
    }


    fun detectKeyboardToggle(view: View): Boolean {
        var showing = false
        view.viewTreeObserver.addOnGlobalLayoutListener {

            val r = Rect()
            view.getWindowVisibleDisplayFrame(r)
            val screenHeight = view.rootView.height

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            val keypadHeight = screenHeight - r.bottom

            showing = keypadHeight > screenHeight * 0.15
        }
        return showing
    }


    fun fromHtml(source: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(source)
        }
    }

    fun getHexColor(r: Int, g: Int, b: Int): String? {
        return String.format("#%02x%02x%02x", r, g, b)
    }


    /*
     * ThongHQ
     *custom Indicator tab layout
     */
    fun reduceMarginsInTabs(tabLayout: TabLayout, marginOffset: Int) {
        val tabStrip = tabLayout.getChildAt(0)
        if (tabStrip is ViewGroup) {
            val tabStripGroup = tabStrip as ViewGroup
            for (i in 0 until (tabStrip as ViewGroup).childCount) {
                val tabView = tabStripGroup.getChildAt(i)
                if (tabView.layoutParams is ViewGroup.MarginLayoutParams) {
                    (tabView.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = marginOffset
                    (tabView.layoutParams as ViewGroup.MarginLayoutParams).rightMargin =
                            marginOffset
                }
            }
            tabLayout.requestLayout()
        }
    }

//    fun convertSencondToStringHourMin(ctx: Context, second: Long): String {
//        val hours = second / 3600
//        val minutes = second % 3600 / 60
//
//        return if (hours > 0) {
//            ctx.getString(R.string.all_txt_date_format_hour_min, format00(hours), format00(minutes))
//        } else {
//            ctx.getString(R.string.all_txt_date_format_min, format00(minutes))
//        }
//    }

    fun format00(i: Int): String {
        return if (i <= 9) "0$i" else i.toString()
    }


    /**
     * hàm sử dụng trong màn hình danh bạ, chat,...
     * hiện thị trong item 1 dòng tên, 1 dòng nội dung xem trước
     *
     * @param mContext
     * @param rootContent
     * @param reviewContent
     * @return
     */
    fun setTextWithReviewContent(mContext: Context, rootContent: String, reviewContent: String): SpannableString? {
        val fontBold = Typeface.createFromAsset(mContext.assets, mContext.getString(R.string.fonts_opensans_bold))
        val fontRegular = Typeface.createFromAsset(mContext.assets, mContext.getString(R.string.fonts_opensans_light))

        //format text rootContent + reviewContent for item
        val str = SpannableString(rootContent + if (reviewContent.isNotEmpty()) "\n" + reviewContent else "")
        str.setSpan(AbsoluteSizeSpan(SizeUtil.spToPx(mContext, 14f)), 0, rootContent.length, 0) //set textSize Name
        str.setSpan(AbsoluteSizeSpan(SizeUtil.spToPx(mContext, 12f)), rootContent.length, str.length, 0) //set textSize Rename
        str.setSpan(CustomTypefaceSpan("", fontBold), 0, rootContent.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        str.setSpan(CustomTypefaceSpan("", fontRegular), rootContent.length, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        str.setSpan(ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.black)), 0, rootContent.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        str.setSpan(ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.main_icon_normal)), rootContent.length, str.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        return str
    }


    fun setTextWithCurrencyFormat(mContext: Context, currency: String, price: String): SpannableString? {
        val fontSemiBold = Typeface.createFromAsset(mContext.assets, mContext.getString(R.string.fonts_opensans_semi_bold))
        val fontBold = Typeface.createFromAsset(mContext.assets, mContext.getString(R.string.fonts_opensans_bold))

        //format text rootContent + reviewContent for item
        val str = SpannableString("$currency ${currencyWithoutDecimal(price.toDouble())}")
        str.setSpan(AbsoluteSizeSpan(SizeUtil.spToPx(mContext, 10f)), 0, currency.length, 0) //set textSize Name
        str.setSpan(AbsoluteSizeSpan(SizeUtil.spToPx(mContext, 18f)), currency.length, str.length, 0) //set textSize Rename
        str.setSpan(CustomTypefaceSpan("", fontSemiBold), 0, currency.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        str.setSpan(CustomTypefaceSpan("", fontBold), currency.length, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        str.setSpan(ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.red_60)), 0, currency.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        str.setSpan(ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.red_60)), currency.length, str.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        return str
    }

    fun setTextWithCurrencyFormatBlack(mContext: Context, currency: String, price: String): SpannableString? {
        val fontSemiBold = Typeface.createFromAsset(mContext.assets, mContext.getString(R.string.fonts_opensans_semi_bold))
        //val fontBold = Typeface.createFromAsset(mContext.assets, mContext.getString(R.string.fonts_opensans_bold))

        val str = SpannableString("$currency ${currencyWithoutDecimal(price.toDouble())}")
        str.setSpan(AbsoluteSizeSpan(SizeUtil.spToPx(mContext, 10f)), 0, currency.length, 0) //set textSize Name
        str.setSpan(AbsoluteSizeSpan(SizeUtil.spToPx(mContext, 14f)), currency.length, str.length, 0) //set textSize Rename
        str.setSpan(CustomTypefaceSpan("", fontSemiBold), 0, currency.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        str.setSpan(CustomTypefaceSpan("", fontSemiBold), currency.length, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        //str.setSpan(ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.red_60)), 0, currency.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        //str.setSpan(ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.red_60)), currency.length, str.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        return str
    }

    fun getContentBold2Reguler(context: Context, boldContent: String?, regulerContent: String?): SpannableString? {
        val fontBold = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_opensans_semi_bold))
        val fontRegular = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_opensans_regular))

        val str = SpannableString("$boldContent $regulerContent")

        boldContent?.length?.plus(1)?.let {
            str.setSpan(CustomTypefaceSpan("", fontBold), 0, it, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)), 0, it, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            str.setSpan(CustomTypefaceSpan("", fontRegular), it, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.color_5f5f5f)), it, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return str
    }


    fun getContentReguler2Bold2Color(context: Context, regulerContent: String?, color1_id: Int, boldContent: String?, color2_id: Int): SpannableString? {
        val fontBold = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_opensans_semi_bold))
        val fontRegular = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_opensans_regular))

        val str = SpannableString("$regulerContent $boldContent")

        regulerContent?.length?.plus(1)?.let {
            str.setSpan(CustomTypefaceSpan("", fontRegular), 0, it, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, color1_id)), 0, it, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            str.setSpan(CustomTypefaceSpan("", fontBold), it, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, color2_id)), it, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return str
    }

    fun getContent2Color(context: Context, content1: String?, color1_id: Int, color2Content: String?, color2_id: Int): SpannableString? {
        val str = SpannableString("$content1 $color2Content")
        content1?.length?.plus(1)?.let {
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, color1_id)), 0, it, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, color2_id)), it, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return str
    }


    fun getContentButtonWithIcon(context: Context, iconID: String?, contentText: String?): SpannableString? {
        val fontBold = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_opensans_semi_bold))
        val fontIcon = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_icon_tuoitre_tv))

        val str = SpannableString("$iconID $contentText")

        iconID?.length?.plus(1)?.let {
            str.setSpan(CustomTypefaceSpan("", fontIcon), 0, it, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(CustomTypefaceSpan("", fontBold), it, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        return str
    }

    fun getContentButtonWithIcon(context: Context, iconID: String?, contentText: String?, fontText: String): SpannableString? {
        val fontBold = Typeface.createFromAsset(context.assets, fontText)
        val fontIcon = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_icon_tuoitre_tv))

        val str = SpannableString("$iconID $contentText")

        iconID?.length?.plus(1)?.let {
            str.setSpan(CustomTypefaceSpan("", fontIcon), 0, it, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(CustomTypefaceSpan("", fontBold), it, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        return str
    }


    fun getContentButtonWithIconEnd(context: Context, iconID: String?, contentText: String?): SpannableString? {
        val fontBold = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_opensans_semi_bold))
        val fontIcon = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_icon_tuoitre_tv))

        val str = SpannableString("$contentText $iconID")

        contentText?.length?.plus(1)?.let {
            str.setSpan(CustomTypefaceSpan("", fontBold), 0, it, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(CustomTypefaceSpan("", fontIcon), it, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        return str
    }


    fun getContentButtonWithIconEnd(context: Context, iconID: String?, iconColorID: Int, contentText: String?, contentColorID: Int): SpannableString? {
        val fontBold = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_opensans_semi_bold))
        val fontIcon = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_icon_tuoitre_tv))

        val str = SpannableString("$contentText $iconID")

        contentText?.length?.plus(1)?.let {
            str.setSpan(CustomTypefaceSpan("", fontBold), 0, it, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(CustomTypefaceSpan("", fontIcon), it, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, contentColorID)), 0, it, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, iconColorID)), it, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return str
    }


    fun getContentButtonWithIconEnd(context: Context, iconID: Int, iconColorID: Int, contentText: Int, contentColorID: Int): SpannableString? {
        val fontBold = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_opensans_semi_bold))
        val fontIcon = Typeface.createFromAsset(context.assets, context.resources.getString(R.string.fonts_icon_tuoitre_tv))

        val str = SpannableString("${context.getString(contentText)} ${context.getString(iconID)}")

        "${context.getString(contentText)}"?.length?.plus(1)?.let {
            str.setSpan(CustomTypefaceSpan("", fontBold), 0, it, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(CustomTypefaceSpan("", fontIcon), it, str.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, contentColorID)), 0, it, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            str.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, iconColorID)), it, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return str
    }

    fun getRealPathFromURI(contentUri: Uri, context: Context): String? {
        var path: String? = null
        val proj = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor = context.contentResolver.query(contentUri, proj, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            path = cursor.getString(columnIndex)
        }
        cursor?.close()
        return path
    }


    // Format Currency 1.000,00
    fun currencyWithDecimal(price: Double?): String? {
        val formatter = DecimalFormat("###,###,###.00")
        return formatter.format(price)
    }

    // Format Currency 1,000,00
    fun currencyWithoutDecimal(price: Double?): String {
        val formatter = DecimalFormat("###,###,###.##")
        return formatter.format(price)
    }

    // Format Currency 1,000,000,000
    fun currencyFormatWithComma(price: Double?): String? {
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        return formatter.format(price)
    }

    // Format Currency 1,000.000
    fun currencyToString(price: Double?): String? {
        val toShow = currencyWithoutDecimal(price)
        return if (toShow.indexOf(".") > 0) {
            currencyWithDecimal(price)
        } else {
            currencyWithoutDecimal(price)
        }
    }

    // Format Currency 120K, 1.200K, 12.892.200
    fun currencyWithK(price: Double): String? {
        var priceK = price.toString()
        priceK = if (price % 1000 == 0.0) {
            currencyWithoutDecimal(price / 1000) + "K"
        } else {
            currencyWithoutDecimal(price)
        }
        return priceK
    }


    fun convertStringToDate(dateTime: String?, format: String?): Date? {
        val sdf = SimpleDateFormat(format, Locale.US)
        try {
            return sdf.parse(dateTime!!.trim())
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    //convert giờ server to local
    fun convertStringToDate(dateTime: String?, format: String, locale: Locale, timeZone: TimeZone): Date? {
        if (dateTime != null) {
            val sdf = SimpleDateFormat(format, locale)
            sdf.timeZone = timeZone
            try {
                return sdf.parse(dateTime.trim { it <= ' ' })
            } catch (e: ParseException) {
                e.printStackTrace()
            }

        }
        return null
    }

    fun convertDateToString(dateTime: Date?, format: String): String {
        if (dateTime != null) {
            val sdf = SimpleDateFormat(format)
            //sdf.timeZone = timeZone
            return sdf.format(dateTime)
        }
        return ""
    }

    fun convertDateToString(dateTime: Date?, format: String, locale: Locale, timeZone: TimeZone): String {
        if (dateTime != null) {
            val sdf = SimpleDateFormat(format, locale)
            sdf.timeZone = timeZone
            return sdf.format(dateTime)
        }
        return ""
    }

    fun convertDateStringToString(dateTime: String?, format: String, locale: Locale, timeZone: TimeZone): String {
        return convertDateToString(convertStringToDate(dateTime, Constants.DETAIL_FORMAT_DATE_SERVER, locale, timeZone), format, locale, timeZone)
    }


    fun showTimeMessageChat(dateMessage: Date?): String? {
        var time = ""
        val cal = Calendar.getInstance()
        val calMesage = Calendar.getInstance()
        calMesage.time = dateMessage
        //calMesage.setTimeZone(timeZone);
        time = if (cal[Calendar.DAY_OF_YEAR] == calMesage[Calendar.DAY_OF_YEAR]) {
            val hour = calMesage[Calendar.HOUR_OF_DAY]
            val min = calMesage[Calendar.MINUTE]
            (if (hour < 10) "0$hour" else hour).toString() + ":" + if (min < 10) "0$min" else min
        } else if (cal[Calendar.WEEK_OF_YEAR] == calMesage[Calendar.WEEK_OF_YEAR]) {
            calMesage.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US)
        } else {
            val day = calMesage[Calendar.DAY_OF_MONTH]
            val month = calMesage[Calendar.MONTH] + 1
            (if (day < 10) "0$day" else day).toString() + "/" + (if (month < 10) "0$month" else month) + "/" + calMesage[Calendar.YEAR]
        }
        return time
    }


    fun showTimeMessageChat(dateMessage: String?, userTimeZone: String): String? {
        val cal = Calendar.getInstance()
        val calMesage = Calendar.getInstance()
        calMesage.time = convertStringToDate(dateMessage, Constants.DETAIL_FORMAT_DATE_SERVER, Locale.US, TimeZone.getTimeZone(userTimeZone))
        val time = if (cal[Calendar.DAY_OF_YEAR] == calMesage[Calendar.DAY_OF_YEAR]) {
            val hour = calMesage[Calendar.HOUR_OF_DAY]
            val min = calMesage[Calendar.MINUTE]
            (if (hour < 10) "0$hour" else hour).toString() + ":" + if (min < 10) "0$min" else min
        } else if (cal[Calendar.WEEK_OF_YEAR] == calMesage[Calendar.WEEK_OF_YEAR]) {
            calMesage.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG_FORMAT, Locale.US)
        } else {
            val day = calMesage[Calendar.DAY_OF_MONTH]
            val month = calMesage[Calendar.MONTH] + 1
            (if (day < 10) "0$day" else day).toString() + "/" + (if (month < 10) "0$month" else month) + "/" + calMesage[Calendar.YEAR]
        }
        return time
    }

    //dành cho doanh nghiệp
    fun getTextSex(context: Context, type: String?): String {
        return when (type) {
            "M" -> context.getString(R.string.piepjob_sex_male)
            "F" -> context.getString(R.string.piepjob_sex_female)
            "O" -> context.getString(R.string.piepjob_sex_other)
            "A" -> context.getString(R.string.piepjob_not_required_sex)
            else -> ""
        }
    }

    fun getTextStatusMarried(context: Context, type: String?): String {
        return when (type) {
            "SIN" -> context.getString(R.string.piepjob_status_single)
            "MAR" -> context.getString(R.string.piepjob_status_married)
            "DIV" -> context.getString(R.string.piepjob_status_divorce)
            else -> context.getString(R.string.piepjob_status_single)
        }
    }

    fun getTextYearOld(context: Context, birthYear: Int?): String {
        return if (birthYear != null && birthYear > 0) {
            val calendar = Calendar.getInstance()
            (calendar.get(Calendar.YEAR) - birthYear).toString() + " " + context.getString(R.string.piepjob_age)
        } else {
            ""
        }
    }

    fun getRoundedCornerBitmap(bitmap: Bitmap, color: Int, cornerDips: Int,
                               borderDips: Int, context: Context): Bitmap {


        val output = Bitmap.createBitmap(bitmap.width, bitmap.height,
                Bitmap.Config.ARGB_8888)

        val canvas = Canvas(output)

        val borderSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, borderDips.toFloat(),
                context.resources.displayMetrics).toInt()
        val cornerSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, cornerDips.toFloat(),
                context.resources.displayMetrics).toInt()
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)

        // prepare canvas for transfer
        paint.isAntiAlias = true
        paint.color = -0x1
        paint.style = Paint.Style.FILL
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawRoundRect(rectF, cornerSizePx.toFloat(), cornerSizePx.toFloat(), paint)

        // draw bitmap
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        // draw border
        paint.color = color
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderSizePx.toFloat()
        canvas.drawRoundRect(rectF, cornerSizePx.toFloat(), cornerSizePx.toFloat(), paint)

        return output
    }

    fun cropBitmap(src: Bitmap, w: Int, h: Int,
                   horizontalCenterPercent: Float, verticalCenterPercent: Float): Bitmap {
        require(!(horizontalCenterPercent < 0 || horizontalCenterPercent > 1 || verticalCenterPercent < 0
                || verticalCenterPercent > 1)) { "horizontalCenterPercent and verticalCenterPercent must be between 0.0f and " + "1.0f, inclusive." }
        val srcWidth = src.width
        val srcHeight = src.height
        // exit early if no resize/crop needed
        if (w == srcWidth && h == srcHeight) {
            return src
        }
        val m = Matrix()
        val scale = Math.max(
                w.toFloat() / srcWidth,
                h.toFloat() / srcHeight)
        m.setScale(scale, scale)
        val srcCroppedW: Int
        val srcCroppedH: Int
        var srcX: Int
        var srcY: Int
        srcCroppedW = Math.round(w / scale)
        srcCroppedH = Math.round(h / scale)
        srcX = (srcWidth * horizontalCenterPercent - srcCroppedW / 2).toInt()
        srcY = (srcHeight * verticalCenterPercent - srcCroppedH / 2).toInt()
        // Nudge srcX and srcY to be within the bounds of src
        srcX = Math.max(Math.min(srcX, srcWidth - srcCroppedW), 0)
        srcY = Math.max(Math.min(srcY, srcHeight - srcCroppedH), 0)
        return Bitmap.createBitmap(src, srcX, srcY, srcCroppedW, srcCroppedH, m,
                true /* filter */)
    }

    /**
     * get key language follow KEY server
     *
     * @param mActivity
     * @param start_content_key tiếp đầu ngữ vd: message_key_noti =>>> message
     * @param key_lang          key từ server trả về sau khi vô hàm sẽ toLowerCase tất cả
     * @return if return "" is is not a valid resource ID
     */
    fun getKeyLanguage(context: Context, start_content_key: String?, key_lang: String?): String? {
//        return context.getString(context.resources.getIdentifier("$start_content_key$key_lang".toLowerCase(), "string", context.packageName))
//                ?: ""

        return try {
            context.getString(context.resources.getIdentifier("$start_content_key$key_lang".toLowerCase(), "string", context.packageName))
        } catch (e: Exception) {
            LogUtil.e("getKeyLanguage1>>>>", " is catchhhhhhh>>>>>>>>>>>>>>>>>>>>>>> $e")
            ""
        }
    }


    fun getKeyLanguage(context: Context, key_lang: String?): String? {
        return try {
            context.getString(context.resources.getIdentifier("$key_lang".toLowerCase(), "string", context.packageName))
        } catch (e: Exception) {
            LogUtil.e("getKeyLanguage>>>>", " is catchhhhhhh>>>>>>>>>>>>>>>>>>>>>>> $e")
            ""
        }
    }

    fun getKeyLanguageDefault(context: Context, key_lang: String?, textDefault: String?): String? {
        return try {
            context.getString(context.resources.getIdentifier("$key_lang".toLowerCase(), "string", context.packageName))
        } catch (e: Exception) {
            textDefault
        }
    }


    // detect apple symbol
    // 🍏 🍎 and show this symbol to replace
    fun showAppleSymbol(content: String): String? {
        var content = content
        if (content.indexOf("") > 0) {
            content = content.replace("".toRegex(), "🍏")
        }
        return content
    }

    fun shareIntent(context: Context, content: String) {
        context.startActivity(Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, content)
            type = "text/plain"
        }, null))
    }


    // round to %.2f decimal: 3.14
    fun roundToDouble(value: Double): Double {
        var multiplier = 1.0
        repeat(1) { multiplier *= 10 }
        return round(value * multiplier) / multiplier
    }

    fun prepareMatrix(matrix: Matrix, mirror: Boolean, displayOrientation: Int,
                      viewWidth: Int, viewHeight: Int) {
        // Need mirror for front camera.
        matrix.setScale((if (mirror) -1 else 1) as Float, 1f)
        // This is the value for android.hardware.Camera.setDisplayOrientation.
        matrix.postRotate(displayOrientation.toFloat())
        // Camera driver coordinates range from (-1000, -1000) to (1000, 1000).
        // UI coordinates range from (0, 0) to (width, height).
        matrix.postScale(viewWidth / 2000f, viewHeight / 2000f)
        matrix.postTranslate(viewWidth / 2f, viewHeight / 2f)
    }

    fun getScreenRotation(context: Context) = when ((context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.rotation) {
        Surface.ROTATION_0 -> 0
        Surface.ROTATION_90 -> 90
        Surface.ROTATION_180 -> 180
        else -> 270
    }


    fun format00(i: Long) = if (i <= 9) "0$i" else "$i"


    fun convert(str: String): String? {
        var str = str
        str = str.replace("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ".toRegex(), "a")
        str = str.replace("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ".toRegex(), "e")
        str = str.replace("ì|í|ị|ỉ|ĩ".toRegex(), "i")
        str = str.replace("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ".toRegex(), "o")
        str = str.replace("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ".toRegex(), "u")
        str = str.replace("ỳ|ý|ỵ|ỷ|ỹ".toRegex(), "y")
        str = str.replace("đ".toRegex(), "d")
        str = str.replace("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ".toRegex(), "A")
        str = str.replace("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ".toRegex(), "E")
        str = str.replace("Ì|Í|Ị|Ỉ|Ĩ".toRegex(), "I")
        str = str.replace("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ".toRegex(), "O")
        str = str.replace("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ".toRegex(), "U")
        str = str.replace("Ỳ|Ý|Ỵ|Ỷ|Ỹ".toRegex(), "Y")
        str = str.replace("Đ".toRegex(), "D")
        return str
    }

    /**
     * @key: fjob
     * return: https://tuoitretimviec.vn/tuyen-dung-gap-fjobsw8l2pCuV9.html
     * https://tuoitretimviec.vn/test-engineer-qa-qc-tester-2021-fjoblsKtltrmcu.html
     */
    fun makeAlias(key: String, PV603: String, PV602: String): String = "https://tuoitretimviec.vn/${convert(PV603)?.replace("[^a-zA-Z0-9\\s]+".toRegex(), "")?.toLowerCase()}-${key}${PV602}.html".replace("\\s+".toRegex(), "-")


    /**
     * set Gravity Gravity CENTERVERTICAL for SpannableString
     */
    class CustomCharacterSpan : MetricAffectingSpan {
        var ratio = 0.5

        constructor() {}
        constructor(ratio: Double) {
            this.ratio = ratio
        }

        override fun updateDrawState(paint: TextPaint) {
            paint.baselineShift += (paint.ascent() * ratio).toInt()
        }

        override fun updateMeasureState(paint: TextPaint) {
            paint.baselineShift += (paint.ascent() * ratio).toInt()
        }
    }
}
