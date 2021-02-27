package com.tulau.core.model

import android.graphics.Color.parseColor
import android.graphics.drawable.Drawable
import com.tulau.core.ConstantView
import com.tulau.core.ConstantView.DEFAULT_TAG_LAYOUT_COLOR
import com.tulau.core.ConstantView.DEFAULT_TAG_TEXT_COLOR

/**
 * Created by Dragon on 1/5/17.
 */

class Tag {


    var id: Int = 0
    var text: String = ""
    var tagTextColor: Int = 0
    var tagTextSize: Float = 0.toFloat()
    var layoutColor: Int = 0
    var layoutColorPress: Int = 0
    var isDeletable: Boolean = false
    var isAddtable: Boolean = false
    var isMoretable: Boolean = false
    var deleteIndicatorColor: Int = 0
    var deleteIndicatorSize: Float = 0.toFloat()
    var addIndicatorColor: Int = 0
    var addIndicatorSize: Float = 0.toFloat()
    var radius: Float = 0.toFloat()
    var deleteIcon: String = ""
    var addIcon: String = ""
    var layoutBorderSize: Float = 0.toFloat()
    var layoutBorderColor: Int = 0
    var background: Drawable? = null
    var isOvalOfAdd: Boolean = false
    var srcImage: String = ""

    constructor(text: String) {
        init(
            0,
            text,
            DEFAULT_TAG_TEXT_COLOR,
            ConstantView.DEFAULT_TAG_TEXT_SIZE,
            DEFAULT_TAG_LAYOUT_COLOR,
            ConstantView.DEFAULT_TAG_LAYOUT_COLOR_PRESS,
            ConstantView.DEFAULT_TAG_IS_DELETABLE,
            ConstantView.DEFAULT_TAG_IS_ADDTABLE,
            false,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_RADIUS,
            ConstantView.DEFAULT_TAG_DELETE_ICON,
            ConstantView.DEFAULT_TAG_ADD_ICON,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_SIZE,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_COLOR,
            false,
                ""
        )

    }

    constructor(text: String, srcImage: String) {
        init(
            0,
            text,
            DEFAULT_TAG_TEXT_COLOR,
            ConstantView.DEFAULT_TAG_TEXT_SIZE,
            DEFAULT_TAG_LAYOUT_COLOR,
            ConstantView.DEFAULT_TAG_LAYOUT_COLOR_PRESS,
            ConstantView.DEFAULT_TAG_IS_DELETABLE,
            ConstantView.DEFAULT_TAG_IS_ADDTABLE,
            false,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_RADIUS,
            ConstantView.DEFAULT_TAG_DELETE_ICON,
            ConstantView.DEFAULT_TAG_ADD_ICON,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_SIZE,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_COLOR,
            false,
                srcImage
        )

    }

    constructor(text: String, color: Int) {
        init(
            0,
            text,
            ConstantView.DEFAULT_TAG_TEXT_COLOR,
            ConstantView.DEFAULT_TAG_TEXT_SIZE,
            color,
            ConstantView.DEFAULT_TAG_LAYOUT_COLOR_PRESS,
            ConstantView.DEFAULT_TAG_IS_DELETABLE,
            ConstantView.DEFAULT_TAG_IS_ADDTABLE,
            false,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_RADIUS,
            ConstantView.DEFAULT_TAG_DELETE_ICON,
            ConstantView.DEFAULT_TAG_ADD_ICON,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_SIZE,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_COLOR,
            false,
                ""
        )

    }

    constructor(id: Int, text: String) {
        init(
            id,
            text,
            ConstantView.DEFAULT_TAG_TEXT_COLOR,
            ConstantView.DEFAULT_TAG_TEXT_SIZE,
            ConstantView.DEFAULT_TAG_LAYOUT_COLOR,
            ConstantView.DEFAULT_TAG_LAYOUT_COLOR_PRESS,
            ConstantView.DEFAULT_TAG_IS_DELETABLE,
            ConstantView.DEFAULT_TAG_IS_ADDTABLE,
            false,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_RADIUS,
            ConstantView.DEFAULT_TAG_DELETE_ICON,
            ConstantView.DEFAULT_TAG_ADD_ICON,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_SIZE,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_COLOR,
            false,
                ""
        )
    }

    constructor(id: Int, text: String, color: Int) {
        init(
            id,
            text,
            ConstantView.DEFAULT_TAG_TEXT_COLOR,
            ConstantView.DEFAULT_TAG_TEXT_SIZE,
            color,
            ConstantView.DEFAULT_TAG_LAYOUT_COLOR_PRESS,
            ConstantView.DEFAULT_TAG_IS_DELETABLE,
            ConstantView.DEFAULT_TAG_IS_ADDTABLE,
            false,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_RADIUS,
            ConstantView.DEFAULT_TAG_DELETE_ICON,
            ConstantView.DEFAULT_TAG_ADD_ICON,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_SIZE,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_COLOR,
            false,
                ""
        )

    }

    constructor(id: Int, text: String, color: String) {
        init(
            id,
            text,
            ConstantView.DEFAULT_TAG_TEXT_COLOR,
            ConstantView.DEFAULT_TAG_TEXT_SIZE,
            parseColor(color),
            ConstantView.DEFAULT_TAG_LAYOUT_COLOR_PRESS,
            ConstantView.DEFAULT_TAG_IS_DELETABLE,
            ConstantView.DEFAULT_TAG_IS_ADDTABLE,
            false,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_DELETE_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_COLOR,
            ConstantView.DEFAULT_TAG_ADD_INDICATOR_SIZE,
            ConstantView.DEFAULT_TAG_RADIUS,
            ConstantView.DEFAULT_TAG_DELETE_ICON,
            ConstantView.DEFAULT_TAG_ADD_ICON,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_SIZE,
            ConstantView.DEFAULT_TAG_LAYOUT_BORDER_COLOR,
            false,
                ""
        )

    }

    private fun init(
        id: Int,
        text: String,
        tagTextColor: Int,
        tagTextSize: Float,
        layout_color: Int,
        layout_color_press: Int,
        isDeletable: Boolean,
        isAddtable: Boolean,
        isMoretable: Boolean,
        deleteIndicatorColor: Int,
        deleteIndicatorSize: Float,
        addIndicatorColor: Int,
        addIndicatorSize: Float,
        radius: Float,
        deleteIcon: String,
        addIcon: String,
        layoutBorderSize: Float,
        layoutBorderColor: Int,
        isOvalOfAdd: Boolean,
        srcImage: String
    ) {
        this.id = id
        this.text = text
        this.tagTextColor = tagTextColor
        this.tagTextSize = tagTextSize
        this.layoutColor = layout_color
        this.layoutColorPress = layout_color_press
        this.isDeletable = isDeletable
        this.isAddtable = isAddtable
        this.isMoretable = isMoretable
        this.deleteIndicatorColor = deleteIndicatorColor
        this.deleteIndicatorSize = deleteIndicatorSize
        this.addIndicatorColor = addIndicatorColor
        this.addIndicatorSize = addIndicatorSize
        this.radius = radius
        this.deleteIcon = deleteIcon
        this.addIcon = addIcon
        this.layoutBorderSize = layoutBorderSize
        this.layoutBorderColor = layoutBorderColor
        this.isOvalOfAdd = isOvalOfAdd
        this.srcImage = srcImage
    }


}
