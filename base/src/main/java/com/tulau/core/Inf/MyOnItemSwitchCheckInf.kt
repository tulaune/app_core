package com.tulau.core.Inf

import com.tulau.core.views.MySwitchButton

interface MyOnItemSwitchCheckInf {
    fun onSwitchCheck(view: MySwitchButton?, data: Any?, position: Int, isChecked: Boolean)
}