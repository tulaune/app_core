package com.tulau.core.views

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.tulau.core.R


/**
 * Created by Tư Lầu on 10/3/17.
 */

object StatusBarBackgroundHelper {
    const val IS_BLACK = 0      //this is default
    const val IS_WHITE = 1
    const val IS_USER = 2
    const val IS_BUSSINESS = 3
    const val IS_BLUE = 4

    var SttStyle: Int = IS_BLACK
    var isNightModeEnabled = false

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarBackground(activity: Activity, getStyleStt: Int) {
        this.SttStyle = getStyleStt
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity.applicationContext!!, R.color.transparent)
            window.navigationBarColor = ContextCompat.getColor(activity.applicationContext!!, R.color.black)

            //khi stt mau trang thi set textcolor lai thanh mau den --> dung trong man hinh detail
            if (SttStyle == IS_WHITE) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }

            window.setBackgroundDrawable(ContextCompat.getDrawable(activity.applicationContext!!, getColorForStatusBar()))
        }
    }


    fun setToolBarBackground(toolBarView: Any) {
        (toolBarView as View).setBackgroundResource(getColorForStatusBar())
    }


    private fun getColorForStatusBar(): Int = if (isNightModeEnabled)
        when (SttStyle) {
            IS_BUSSINESS -> R.drawable.bg_toolbar_business_status_darkmode
            IS_BLUE -> R.drawable.bg_toolbar_status_blue_darkmode
            else -> R.drawable.bg_toolbar_status_black_darkmode
        }
    else when (SttStyle) {
        IS_BUSSINESS -> R.drawable.bg_toolbar_business
        IS_USER -> R.drawable.bg_toolbar
        IS_WHITE -> R.drawable.bg_toolbar_status_white
        IS_BLUE -> R.drawable.bg_toolbar_status_blue
        else -> R.drawable.bg_toolbar_status_back
    }
}
