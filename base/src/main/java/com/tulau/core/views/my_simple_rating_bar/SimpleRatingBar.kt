package com.tulau.core.views.my_simple_rating_bar

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

/**
 * Created by Tư Lầu on 2/28/18.
 */
internal interface SimpleRatingBar {
    var numStars: Int
    var rating: Float
    var starPadding: Int
    fun setEmptyDrawable(drawable: Drawable?)
    fun setEmptyDrawableRes(@DrawableRes res: Int)
    fun setFilledDrawable(drawable: Drawable?)
    fun setFilledDrawableRes(@DrawableRes res: Int)
}