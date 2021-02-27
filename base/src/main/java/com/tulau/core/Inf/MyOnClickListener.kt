package com.tulau.core.Inf

import android.os.SystemClock
import android.view.View

abstract class MyOnClickListener : View.OnClickListener {

    internal var mLastClickTime: Long = 0

    abstract fun oneClick(v: View)

    override fun onClick(v: View) {
        // mis-clicking prevention, using threshold of 1000 ms
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        oneClick(v)
    }


}
