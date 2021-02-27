package com.tulau.core.views

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.animation.ValueAnimator.*
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.tulau.core.R

/**
 * Copyright (C) 2017 Cliff Ophalvens (Blogc.at)
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Cliff Ophalvens (Blogc.at)
 */
class ExpandableTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : MyTextView(context, attrs!!, defStyle) {
    private val onExpandListeners: MutableList<OnExpandListener>
    /**
     * Returns the current [TimeInterpolator] for expanding.
     *
     * @return the current interpolator, null by default.
     */
    /**
     * Sets a [TimeInterpolator] for expanding.
     *
     * @param expandInterpolator the interpolator
     */
    var expandInterpolator: TimeInterpolator? = null
    /**
     * Returns the current [TimeInterpolator] for collapsing.
     *
     * @return the current interpolator, null by default.
     */
    /**
     * Sets a [TimeInterpolator] for collpasing.
     *
     * @param collapseInterpolator the interpolator
     */
    var collapseInterpolator: TimeInterpolator? = null

    private var maxLines: Int? = 0
    private var animationDuration: Long = 0
    private var animating: Boolean = false

    /**
     * Is this [ExpandableTextView] expanded or not?
     *
     * @return true if expanded, false if collapsed.
     */
    var isExpanded: Boolean = false
        private set
    private var collapsedHeight: Int = 0

    init {

        // read attributes
        val attributes =
                context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView, defStyle, 0)
        this.animationDuration = attributes.getInt(
                R.styleable.ExpandableTextView_animation_duration, /*BuildConfig.DEFAULT_ANIMATION_DURATION*/
                0
        ).toLong()
        attributes.recycle()

        // keep the original value of maxLines
        this.maxLines = this.getMaxLines()

        // create bucket of OnExpandListener instances
        this.onExpandListeners = ArrayList()

        // create default interpolators
        this.expandInterpolator = AccelerateDecelerateInterpolator()
        this.collapseInterpolator = AccelerateDecelerateInterpolator()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        // if this TextView is collapsed and maxLines = 0,
        // than make its height equals to zero
        if (this.maxLines == 0 && !this.isExpanded && !this.animating) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY)
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    //region public helper methods

    /**
     * Toggle the expanded state of this [ExpandableTextView].
     *
     * @return true if toggled, false otherwise.
     */
    fun toggle(): Boolean {
        return if (this.isExpanded)
            this.collapse()
        else
            this.expand()
    }

    /**
     * Expand this [ExpandableTextView].
     *
     * @return true if expanded, false otherwise.
     */
    fun expand(): Boolean {
        if (!this.isExpanded && !this.animating && this.maxLines!! >= 0) {
            // notify listener
            this.notifyOnExpand()

            // measure collapsed height
            this.measure(
                    MeasureSpec.makeMeasureSpec(this.measuredWidth, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )

            this.collapsedHeight = this.measuredHeight

            // indicate that we are now animating
            this.animating = true

            // set maxLines to MAX Integer, so we can calculate the expanded height
            this.setMaxLines(Integer.MAX_VALUE)

            // measure expanded height
            this.measure(
                    MeasureSpec.makeMeasureSpec(this.measuredWidth, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )

            val expandedHeight = this.measuredHeight

            // animate from collapsed height to expanded height
            val valueAnimator = ofInt(this.collapsedHeight, expandedHeight)
            valueAnimator.addUpdateListener { animation ->
                this@ExpandableTextView.height = animation.animatedValue as Int
            }

            // wait for the animation to end
            valueAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    // reset min & max height (previously set with setHeight() method)
                    this@ExpandableTextView.maxHeight = Integer.MAX_VALUE
                    this@ExpandableTextView.minHeight = 0

                    // if fully expanded, set height to WRAP_CONTENT, because when rotating the device
                    // the height calculated with this ValueAnimator isn't correct anymore
                    val layoutParams = this@ExpandableTextView.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    this@ExpandableTextView.layoutParams = layoutParams

                    // keep track of current status
                    this@ExpandableTextView.isExpanded = true
                    this@ExpandableTextView.animating = false
                }
            })

            // set interpolator
            valueAnimator.interpolator = this.expandInterpolator

            // start the animation
            valueAnimator
                    .setDuration(this.animationDuration)
                    .start()

            return true
        }

        return false
    }

    /**
     * Collapse this [TextView].
     *
     * @return true if collapsed, false otherwise.
     */
    fun collapse(): Boolean {
        if (this.isExpanded && !this.animating && this.maxLines!! >= 0) {
            // notify listener
            this.notifyOnCollapse()

            // measure expanded height
            val expandedHeight = this.measuredHeight

            // indicate that we are now animating
            this.animating = true

            // animate from expanded height to collapsed height
            val valueAnimator = ofInt(expandedHeight, this.collapsedHeight)
            valueAnimator.addUpdateListener { animation ->
                this@ExpandableTextView.height = animation.animatedValue as Int
            }

            // wait for the animation to end
            valueAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    // keep track of current status
                    this@ExpandableTextView.isExpanded = false
                    this@ExpandableTextView.animating = false

                    // set maxLines back to original value
                    this@ExpandableTextView.setMaxLines(this@ExpandableTextView.maxLines!!)

                    // if fully collapsed, set height back to WRAP_CONTENT, because when rotating the device
                    // the height previously calculated with this ValueAnimator isn't correct anymore
                    val layoutParams = this@ExpandableTextView.layoutParams
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    this@ExpandableTextView.layoutParams = layoutParams
                }
            })

            // set interpolator
            valueAnimator.interpolator = this.collapseInterpolator

            // start the animation
            valueAnimator
                    .setDuration(this.animationDuration)
                    .start()

            return true
        }

        return false
    }

    //endregion

    //region public getters and setters

    /**
     * Sets the duration of the expand / collapse animation.
     *
     * @param animationDuration duration in milliseconds.
     */
    fun setAnimationDuration(animationDuration: Long) {
        this.animationDuration = animationDuration
    }

    /**
     * Adds a listener which receives updates about this [ExpandableTextView].
     *
     * @param onExpandListener the listener.
     */
    fun addOnExpandListener(onExpandListener: OnExpandListener) {
        this.onExpandListeners.add(onExpandListener)
    }

    /**
     * Removes a listener which receives updates about this [ExpandableTextView].
     *
     * @param onExpandListener the listener.
     */
    fun removeOnExpandListener(onExpandListener: OnExpandListener) {
        this.onExpandListeners.remove(onExpandListener)
    }

    /**
     * Sets a [TimeInterpolator] for expanding and collapsing.
     *
     * @param interpolator the interpolator
     */
    fun setInterpolator(interpolator: TimeInterpolator) {
        this.expandInterpolator = interpolator
        this.collapseInterpolator = interpolator
    }

    //endregion

    /**
     * This method will notify the listener about this view being expanded.
     */
    private fun notifyOnCollapse() {
        for (onExpandListener in this.onExpandListeners) {
            onExpandListener.onCollapse(this)
        }
    }

    /**
     * This method will notify the listener about this view being collapsed.
     */
    private fun notifyOnExpand() {
        for (onExpandListener in this.onExpandListeners) {
            onExpandListener.onExpand(this)
        }
    }


    //region public interfaces

    /**
     * Interface definition for a callback to be invoked when
     * a [ExpandableTextView] is expanded or collapsed.
     */
    interface OnExpandListener {
        /**
         * The [ExpandableTextView] is being expanded.
         *
         * @param view the textview
         */
        fun onExpand(view: ExpandableTextView)

        /**
         * The [ExpandableTextView] is being collapsed.
         *
         * @param view the textview
         */
        fun onCollapse(view: ExpandableTextView)
    }

    /**
     * Simple implementation of the [OnExpandListener] interface with stub
     * implementations of each method. Extend this if you do not intend to override
     * every method of [OnExpandListener].
     */
    class SimpleOnExpandListener : OnExpandListener {
        override fun onExpand(view: ExpandableTextView) {
            // empty implementation
        }

        override fun onCollapse(view: ExpandableTextView) {
            // empty implementation
        }
    }

    //gọi hàm này để bâm nút xem thêm
    fun onSeenMoreClicked(mtvSeenMore: MyTextView?) {
        post {
            if (text.isNotEmpty() && text.length > 150 /*|| lineCount > maxLines!!*/) {
                mtvSeenMore?.visibility = View.VISIBLE
                mtvSeenMore?.text = context.getString(R.string.pieper_player_view_more)
                mtvSeenMore?.setOnClickListener {
                    if (isExpanded) {
                        collapse()
                        mtvSeenMore.text = context.getString(R.string.pieper_player_view_more)
                    } else {
                        expand()
                        mtvSeenMore.text = context.getString(R.string.pieper_player_view_less)
                    }
                }
            } else {
                mtvSeenMore?.visibility = View.GONE
            }
        }
    }


    //gọi hàm này để bâm nút xem thêm!!!
    //nút xem thêm sẽ k ẩn đi trong bất cứ trường hợp nào như hàm trên
    fun onSeenMoreClickedShow(mtvSeenMore: MyTextView?) {
        this.post {
            if (this.text?.isNotEmpty()!! && this.visibility == View.VISIBLE) {
                mtvSeenMore?.visibility = View.VISIBLE
                mtvSeenMore?.setOnClickListener {
                    if (this.isExpanded) {
                        this.collapse()
                    } else {
                        this.expand()
                    }
                }
            } else {
                mtvSeenMore?.visibility = View.VISIBLE
            }
        }

    }
}