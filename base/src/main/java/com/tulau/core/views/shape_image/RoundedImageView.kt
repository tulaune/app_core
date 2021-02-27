/*
* Copyright (C) 2015 Vincent Mi
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.tulau.core.views.shape_image

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.tulau.core.R

class RoundedImageView : AppCompatImageView {
    var startImageSize: Bitmap? = null
        private set

    private var mBackgroundDrawable: Drawable? = null
    var borderColors: ColorStateList? = ColorStateList.valueOf(RoundedDrawable.DEFAULT_BORDER_COLOR)
        private set
    private var mBorderWidth = DEFAULT_BORDER_WIDTH
    private var mColorFilter: ColorFilter? = null
    private var mColorMod = false
    private var mDrawable: Drawable? = null
    private var mHasColorFilter = false
    private var mIsOval = false
    private var mMutateBackground = false
    private var mResource: Int = 0
    private var mBackgroundResource: Int = 0

    private var mScaleType: ScaleType = ScaleType.FIT_CENTER

    private var tileModeX: Shader.TileMode? = DEFAULT_TILE_MODE
        set(tileModeX) {
            if (this.tileModeX == tileModeX) {
                return
            }

            field = tileModeX
            updateDrawableAttrs()
            updateBackgroundDrawableAttrs(false)
            invalidate()
        }
    private var tileModeY: Shader.TileMode? = DEFAULT_TILE_MODE
        set(tileModeY) {
            if (this.tileModeY == tileModeY) {
                return
            }

            field = tileModeY
            updateDrawableAttrs()
            updateBackgroundDrawableAttrs(false)
            invalidate()
        }

    /**
     * @return the largest corner radius.
     */
    /**
     * Set the corner radii of all corners in px.
     *
     * @param radius the radius to set.
     */
    var cornerRadius: Float
        get() = maxCornerRadius
        set(radius) = setCornerRadius(radius, radius, radius, radius)

    /**setResource
     * @return the largest corner radius.
     */
    val maxCornerRadius: Float
        get() {
            var maxRadius = 0f
            for (r in mCornerRadii) {
                maxRadius = Math.max(r, maxRadius)
            }
            return maxRadius
        }

    var borderColor: Int
        @ColorInt
        get() = borderColors!!.defaultColor
        set(@ColorInt color) = setBorderColor(ColorStateList.valueOf(color))

    var isOval: Boolean
        get() = mIsOval
        set(oval) {
            mIsOval = oval
            updateDrawableAttrs()
            updateBackgroundDrawableAttrs(false)
            invalidate()
        }

    constructor(context: Context) : super(context) {}

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet, defStyle: Int = 0) : super(context, attrs, defStyle) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView, defStyle, 0)

        val index = a.getInt(R.styleable.RoundedImageView_android_scaleType, -1)
        scaleType = if (index >= 0) {
            SCALE_TYPES[index]
        } else {
            // default scaletype to FIT_CENTER
            ScaleType.FIT_CENTER
        }

        var cornerRadiusOverride =
                a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius, -1).toFloat()

        mCornerRadii[Corner.TOP_LEFT] =
                a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_left, -1)
                        .toFloat()
        mCornerRadii[Corner.TOP_RIGHT] =
                a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_right, -1)
                        .toFloat()
        mCornerRadii[Corner.BOTTOM_RIGHT] =
                a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_right, -1)
                        .toFloat()
        mCornerRadii[Corner.BOTTOM_LEFT] =
                a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_left, -1)
                        .toFloat()

        var any = false
        run {
            var i = 0
            val len = mCornerRadii.size
            while (i < len) {
                if (mCornerRadii[i] < 0) {
                    mCornerRadii[i] = 0f
                } else {
                    any = true
                }
                i++
            }
        }

        if (!any) {
            if (cornerRadiusOverride < 0) {
                cornerRadiusOverride = DEFAULT_RADIUS
            }
            var i = 0
            val len = mCornerRadii.size
            while (i < len) {
                mCornerRadii[i] = cornerRadiusOverride
                i++
            }
        }

        mBorderWidth =
                a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_border_width, -1).toFloat()
        if (mBorderWidth < 0) {
            mBorderWidth = DEFAULT_BORDER_WIDTH
        }

        borderColors = a.getColorStateList(R.styleable.RoundedImageView_riv_border_color)
        if (borderColors == null) {
            borderColors = ColorStateList.valueOf(RoundedDrawable.DEFAULT_BORDER_COLOR)
        }

        mMutateBackground = a.getBoolean(R.styleable.RoundedImageView_riv_mutate_background, false)
        mIsOval = a.getBoolean(R.styleable.RoundedImageView_riv_oval, false)

        val tileMode = a.getInt(R.styleable.RoundedImageView_riv_tile_mode, TILE_MODE_UNDEFINED)
        if (tileMode != TILE_MODE_UNDEFINED) {
            tileModeX = parseTileMode(tileMode)
            tileModeY = parseTileMode(tileMode)
        }

//        var tileModeX = a.getInt(R.styleable.RoundedImageView_riv_tile_mode_x, TILE_MODE_UNDEFINED)
//        if (tileModeX != TILE_MODE_UNDEFINED) {
//            tileModeX = parseTileMode(tileModeX) as Int
//        }
//
//        var tileModeY = a.getInt(R.styleable.RoundedImageView_riv_tile_mode_y, TILE_MODE_UNDEFINED)
//        if (tileModeY != TILE_MODE_UNDEFINED) {
//            tileModeY = parseTileMode(tileModeY) as Int
//
//        }

        updateDrawableAttrs()
        updateBackgroundDrawableAttrs(true)

        if (mMutateBackground) {
            // when setBackground() is called by View constructor, mMutateBackground is not loaded from the attribute,
            // so it's false by default, what doesn't allow to create the RoundedDrawable. At this point, after load
            // mMutateBackground and updated BackgroundDrawable to RoundedDrawable, the View's background drawable needs to
            // be changed to this new drawable.

            super.setBackgroundDrawable(mBackgroundDrawable)
        }

        a.recycle()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        invalidate()
    }

    override fun getScaleType(): ScaleType {
        return mScaleType
    }

    override fun setScaleType(scaleType: ScaleType?) {
        assert(scaleType != null)

        if (mScaleType != scaleType) {
            mScaleType = scaleType!!

            when (scaleType) {
                ScaleType.CENTER, ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE, ScaleType.FIT_CENTER, ScaleType.FIT_START, ScaleType.FIT_END, ScaleType.FIT_XY -> super.setScaleType(
                        ScaleType.FIT_XY
                )
                else -> super.setScaleType(scaleType)
            }

            updateDrawableAttrs()
            updateBackgroundDrawableAttrs(false)
            invalidate()
        }
    }

    override fun setImageDrawable(drawable: Drawable?) {
        if (drawable != null) {
            mResource = 0
            mDrawable = RoundedDrawable.fromDrawable(drawable)
            updateDrawableAttrs()
            super.setImageDrawable(mDrawable)
        }
    }

    override fun setImageBitmap(bm: Bitmap?) {
        if (bm != null) {
            mResource = 0
            mDrawable = RoundedDrawable.fromBitmap(bm)
            updateDrawableAttrs()
            super.setImageDrawable(mDrawable)
        }
    }

    override fun setImageResource(@DrawableRes resId: Int) {
        if (mResource != resId) {
            mResource = resId
            mDrawable = resolveResource()
            updateDrawableAttrs()
            super.setImageDrawable(mDrawable)
        }
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        setImageDrawable(drawable)
    }

    private fun resolveResource(): Drawable? {
        val rsrc = resources ?: return null

        var d: Drawable? = null

        if (mResource != 0) {
            try {
                d = rsrc.getDrawable(mResource)
            } catch (e: Exception) {
                Log.w(TAG, "Unable to find resource: $mResource", e)
                // Don't try again.
                mResource = 0
            }

        }
        return RoundedDrawable.fromDrawable(d)
    }

    override fun setBackground(background: Drawable) {
        mBackgroundDrawable = background
        updateBackgroundDrawableAttrs(true)
//        super.setBackground(background)
    }

    override fun setBackgroundResource(@DrawableRes resId: Int) {
        if (mBackgroundResource != resId) {
            mBackgroundResource = resId
            mBackgroundDrawable = resolveBackgroundResource()
            background = mBackgroundDrawable as ColorDrawable
        }
    }

    override fun setBackgroundColor(color: Int) {
        mBackgroundDrawable = ColorDrawable(color)
        background = mBackgroundDrawable as ColorDrawable
    }

    private fun resolveBackgroundResource(): Drawable? {
        val rsrc = resources ?: return null

        var d: Drawable? = null

        if (mBackgroundResource != 0) {
            try {
                d = rsrc.getDrawable(mBackgroundResource)
            } catch (e: Exception) {
                Log.w(TAG, "Unable to find resource: $mBackgroundResource", e)
                // Don't try again.
                mBackgroundResource = 0
            }

        }
        return RoundedDrawable.fromDrawable(d)
    }

    private fun updateDrawableAttrs() {
        updateAttrs(mDrawable, mScaleType)
    }

    private fun updateBackgroundDrawableAttrs(convert: Boolean) {
        if (mMutateBackground) {
            if (convert) {
                mBackgroundDrawable = RoundedDrawable.fromDrawable(mBackgroundDrawable)
            }
            updateAttrs(mBackgroundDrawable, ScaleType.FIT_XY)
        }
    }

    override fun setColorFilter(cf: ColorFilter) {
        if (mColorFilter !== cf) {
            mColorFilter = cf
            mHasColorFilter = true
            mColorMod = true
            applyColorMod()
            invalidate()
        }
    }

    private fun applyColorMod() {
        // Only mutate and apply when modifications have occurred. This should
        // not reset the mColorMod flag, since these filters need to be
        // re-applied if the Drawable is changed.
        if (mDrawable != null && mColorMod) {
            mDrawable = mDrawable!!.mutate()
            if (mHasColorFilter) {
                mDrawable!!.colorFilter = mColorFilter
            }
            //mDrawable.setXfermode(mXfermode);
            //mDrawable.setAlpha(mAlpha * mViewAlphaScale >> 8);
        }
    }

    private fun updateAttrs(drawable: Drawable?, scaleType: ScaleType) {
        if (drawable == null) {
            return
        }

        if (drawable is RoundedDrawable) {
            drawable.setScaleType(scaleType)
                    .setBorderWidth(mBorderWidth)
                    .setBorderColor(borderColors)
                    .setOval(mIsOval)
                    .setTileModeX(tileModeX)
                    .setTileModeY(tileModeY)

            drawable.setCornerRadius(
                    mCornerRadii[Corner.TOP_LEFT],
                    mCornerRadii[Corner.TOP_RIGHT],
                    mCornerRadii[Corner.BOTTOM_RIGHT],
                    mCornerRadii[Corner.BOTTOM_LEFT]
            )

            applyColorMod()
        } else if (drawable is LayerDrawable) {
            // loop through layers to and set drawable attrs
            val ld = drawable as LayerDrawable?
            var i = 0
            val layers = ld?.numberOfLayers
            while (i < layers!!) {
                updateAttrs(ld.getDrawable(i), scaleType)
                i++
            }
        }
    }

    @Deprecated("")
    override fun setBackgroundDrawable(background: Drawable?) {
        mBackgroundDrawable = background
        updateBackgroundDrawableAttrs(true)
        //super.setBackground(mBackgroundDrawable as ColorDrawable)
        this.background = mBackgroundDrawable as ColorDrawable
    }

    /**
     * Get the corner radius of a specified corner.
     *
     * @param corner the corner.
     * @return the radius.
     */
    fun getCornerRadius(@Corner corner: Int): Float {
        return mCornerRadii[corner]
    }

    /**
     * Set all the corner radii from a dimension resource id.
     *
     * @param resId dimension resource id of radii.
     */
    fun setCornerRadiusDimen(@DimenRes resId: Int) {
        val radius = resources.getDimension(resId)
        setCornerRadius(radius, radius, radius, radius)
    }

    /**
     * Set the corner radius of a specific corner from a dimension resource id.
     *
     * @param corner the corner to set.
     * @param resId  the dimension resource id of the corner radius.
     */
    fun setCornerRadiusDimen(@Corner corner: Int, @DimenRes resId: Int) {
        setCornerRadius(corner, resources.getDimensionPixelSize(resId).toFloat())
    }

    /**
     * Set the corner radius of a specific corner in px.
     *
     * @param corner the corner to set.
     * @param radius the corner radius to set in px.
     */
    fun setCornerRadius(@Corner corner: Int, radius: Float) {
        if (mCornerRadii[corner] == radius) {
            return
        }
        mCornerRadii[corner] = radius

        updateDrawableAttrs()
        updateBackgroundDrawableAttrs(false)
        invalidate()
    }

    /**
     * Set the corner radii of each corner individually. Currently only one unique nonzero value is
     * supported.
     *
     * @param topLeft     radius of the top left corner in px.
     * @param topRight    radius of the top right corner in px.
     * @param bottomRight radius of the bottom right corner in px.
     * @param bottomLeft  radius of the bottom left corner in px.
     */
    fun setCornerRadius(topLeft: Float, topRight: Float, bottomLeft: Float, bottomRight: Float) {
        if (mCornerRadii[Corner.TOP_LEFT] == topLeft
                && mCornerRadii[Corner.TOP_RIGHT] == topRight
                && mCornerRadii[Corner.BOTTOM_RIGHT] == bottomRight
                && mCornerRadii[Corner.BOTTOM_LEFT] == bottomLeft
        ) {
            return
        }

        mCornerRadii[Corner.TOP_LEFT] = topLeft
        mCornerRadii[Corner.TOP_RIGHT] = topRight
        mCornerRadii[Corner.BOTTOM_LEFT] = bottomLeft
        mCornerRadii[Corner.BOTTOM_RIGHT] = bottomRight

        updateDrawableAttrs()
        updateBackgroundDrawableAttrs(false)
        invalidate()
    }

    fun getBorderWidth(): Float {
        return mBorderWidth
    }

    fun setBorderWidth(@DimenRes resId: Int) {
        setBorderWidth(resources.getDimension(resId))
    }

    fun setBorderWidth(width: Float) {
        if (mBorderWidth == width) {
            return
        }

        mBorderWidth = width
        updateDrawableAttrs()
        updateBackgroundDrawableAttrs(false)
        invalidate()
    }

    fun setBorderColor(colors: ColorStateList?) {
        if (borderColors == colors) {
            return
        }

        borderColors = colors ?: ColorStateList.valueOf(RoundedDrawable.DEFAULT_BORDER_COLOR)
        updateDrawableAttrs()
        updateBackgroundDrawableAttrs(false)
        if (mBorderWidth > 0) {
            invalidate()
        }
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams) {
        super.setLayoutParams(params)
        if (this.drawable != null) {
            if (startImageSize != null) {
                val backgroundBitmap = scaleBitmap(startImageSize!!, params.width, params.height)
                mDrawable = RoundedDrawable.fromBitmap(backgroundBitmap)
                updateDrawableAttrs()
                super.setImageDrawable(mDrawable)
            }
        }
    }

    fun setStartSizeAvatar(mStartSizeAvatar: Bitmap) {
        if (this.startImageSize == null)
            this.startImageSize = mStartSizeAvatar
    }


    fun setStartSizeAvatarCustom(mStartSizeAvatar: Bitmap) {
        this.startImageSize = mStartSizeAvatar
    }

    fun updateImageSize(width: Int, height: Int) {
        if (this.drawable != null) {
            var backgroundBitmap = (this.drawable as RoundedDrawable).sourceBitmap
            if (startImageSize == null)
                startImageSize = backgroundBitmap

            backgroundBitmap = scaleBitmap(startImageSize!!, width, height)
            mDrawable = RoundedDrawable.fromBitmap(backgroundBitmap)
            updateDrawableAttrs()
            super.setImageDrawable(mDrawable)
        }
    }

    private fun scaleBitmap(bitmap: Bitmap, wantedWidth: Int, wantedHeight: Int): Bitmap {
        val output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val m = Matrix()
        m.setScale(wantedWidth.toFloat() / bitmap.width, wantedHeight.toFloat() / bitmap.height)
        canvas.drawBitmap(bitmap, m, Paint())

        return output
    }

    fun mutatesBackground(): Boolean {
        return mMutateBackground
    }

    fun mutateBackground(mutate: Boolean) {
        if (mMutateBackground == mutate) {
            return
        }

        mMutateBackground = mutate
        updateBackgroundDrawableAttrs(true)
        invalidate()
    }

    companion object {
        // Constants for tile mode attributes
        private val TILE_MODE_UNDEFINED = -2
        private val TILE_MODE_CLAMP = 0
        private val TILE_MODE_REPEAT = 1
        private val TILE_MODE_MIRROR = 2

        val TAG = "RoundedImageView"
        val DEFAULT_RADIUS = 0f
        val DEFAULT_BORDER_WIDTH = 0f
        val DEFAULT_TILE_MODE: Shader.TileMode = Shader.TileMode.CLAMP

        val mCornerRadii =
                floatArrayOf(DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS)

        private val SCALE_TYPES = arrayOf(
                ScaleType.MATRIX,
                ScaleType.FIT_XY,
                ScaleType.FIT_START,
                ScaleType.FIT_CENTER,
                ScaleType.FIT_END,
                ScaleType.CENTER,
                ScaleType.CENTER_CROP,
                ScaleType.CENTER_INSIDE
        )

        private fun parseTileMode(tileMode: Int): Shader.TileMode? {
            return when (tileMode) {
                TILE_MODE_CLAMP -> Shader.TileMode.CLAMP
                TILE_MODE_REPEAT -> Shader.TileMode.REPEAT
                TILE_MODE_MIRROR -> Shader.TileMode.MIRROR
                else -> null
            }
        }
    }
}
