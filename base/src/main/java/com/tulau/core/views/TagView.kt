package com.tulau.core.views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.NonNull
import com.tulau.core.ConstantView
import com.tulau.core.R
import com.tulau.core.model.Tag
import com.tulau.core.utils.SizeUtil
import java.util.*

/**
 * Android TagView Widget
 */
open class TagView : RelativeLayout {

    private var mWidth: Int = 0
    var tagHint: String = ""
        private set
    var lineMargin: Int = 0
        private set
    var tagMargin: Int = 0
        private set
    var textPaddingLeft: Int = 0
        private set
    var textPaddingRight: Int = 0
        private set
    var textPaddingTop: Int = 0
        private set
    var texPaddingBottom: Int = 0
        private set
    private var marginParent: Int = 0
    private var scrollHorizonal: Boolean = false
    val tags: ArrayList<Any> = ArrayList<Any>()
    private var mInflater: LayoutInflater? = null
    private var mClickListener: OnTagClickListener? = null
    private var mAddListener: OnTagAddListener? = null
    private var mDeleteListener: OnTagDeleteListener? = null
    private var size22: Int = 0
    private var size12: Int = 0

    interface OnTagClickListener {
        fun onTagClick(position: Int, tag: Tag)
    }

    interface OnTagAddListener {
        fun onTagAdd(position: Int, tag: Tag)
    }

    interface OnTagDeleteListener {
        fun onTagDeleted(position: Int, tag: Tag)
    }

    constructor(context: Context) : super(context, null) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    constructor(ctx: Context, attrs: AttributeSet, defStyle: Int) : super(ctx, attrs, defStyle) {
        init(ctx, attrs, defStyle, defStyle)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int, defStyleRes: Int) {

        mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // get AttributeSet
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.TagView, defStyle, defStyleRes)
        this.lineMargin = typeArray.getDimension(
                R.styleable.TagView_lineMargin,
                SizeUtil.dpToPx(this.context, ConstantView.DEFAULT_LINE_MARGIN).toFloat()
        ).toInt()
        this.marginParent = typeArray.getDimension(
                R.styleable.TagView_marginParent,
                SizeUtil.dpToPx(this.context, ConstantView.LAYOUT_WIDTH_OFFSET).toFloat()
        ).toInt()
        this.tagMargin = typeArray.getDimension(
                R.styleable.TagView_tagMargin,
                SizeUtil.dpToPx(this.context, ConstantView.DEFAULT_TAG_MARGIN).toFloat()
        ).toInt()
        this.textPaddingLeft = typeArray.getDimension(
                R.styleable.TagView_textPaddingLeft,
                SizeUtil.dpToPx(this.context, ConstantView.DEFAULT_TAG_TEXT_PADDING_LEFT).toFloat()
        ).toInt()
        this.textPaddingRight = typeArray.getDimension(
                R.styleable.TagView_textPaddingRight,
                SizeUtil.dpToPx(this.context, ConstantView.DEFAULT_TAG_TEXT_PADDING_RIGHT).toFloat()
        ).toInt()
        this.textPaddingTop = typeArray.getDimension(
                R.styleable.TagView_textPaddingTop,
                SizeUtil.dpToPx(this.context, ConstantView.DEFAULT_TAG_TEXT_PADDING_TOP).toFloat()
        ).toInt()
        this.texPaddingBottom = typeArray.getDimension(
                R.styleable.TagView_textPaddingBottom,
                SizeUtil.dpToPx(this.context, ConstantView.DEFAULT_TAG_TEXT_PADDING_BOTTOM).toFloat()
        ).toInt()
        this.scrollHorizonal = typeArray.getBoolean(R.styleable.TagView_scrollHorizonal, false)
        typeArray.recycle()

        mWidth = SizeUtil.getScreenWidth(context)
        size22 = SizeUtil.dpToPx(context, 22f)
        size12 = SizeUtil.dpToPx(context, 2f + 10)
        // this.setWillNotDraw(false);


     /*   this.tagHint = typeArray?.getString(R.styleable.TagView_tagHint)
                ?: ""

        if (this.tagHint.isNotEmpty()) {
            val mtvHit = MyTextView(this.context)
            mtvHit.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            mtvHit.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            mtvHit.setTextColor(Color.parseColor("#bcbcbc"))
            mtvHit.textSize = 13f
            mtvHit.ellipsize = TextUtils.TruncateAt.END
            mtvHit.maxLines = 1
            mtvHit.text = this.tagHint
            this.addView(mtvHit)
        }*/

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        marginParent = 0
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        /*int width = getMeasuredWidth();
        if (width <= 0) return;
        mWidth = getMeasuredWidth();
        drawTags();*/
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // View#onDraw is disabled in view group;
        // enable View#onDraw for view group : View#setWillNotDraw(false);
        // drawTags();
    }

    override fun onVisibilityChanged(@NonNull changedView: View, visibility: Int) {
        /*if (changedView == this){
            if (visibility == View.VISIBLE){
                drawTags();
            }
        }*/
        super.onVisibilityChanged(changedView, visibility)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    fun drawTags() {
        try{
            if (visibility != View.VISIBLE) return
            // clear all tag
            removeAllViews()

            // layout padding left & layout padding right
            var total = (paddingLeft + paddingRight).toFloat()

            var listIndex = 1// List Index
            var index_bottom = 1// The Tag to add below
            var index_header = 1// The header tag of this line
            var tag_pre: Tag? = null
            for (item in tags) {
                val position = listIndex - 1

                // inflate tag layout
                val tagLayout = mInflater!!.inflate(R.layout.tagview_item, null)
                val tv_tag_item_contain = tagLayout.findViewById<MyTextView>(R.id.tv_tag_item_contain)
                val tv_tag_item_delete =  tagLayout.findViewById<ImageView>(R.id.tv_tag_item_delete)
                tagLayout.id = listIndex
                tagLayout.background = getSelector(item as Tag)
                // tag text
                tv_tag_item_contain.text = item.text
                val params = tv_tag_item_contain.layoutParams as FrameLayout.LayoutParams
                if (!item.isOvalOfAdd && !item.isMoretable) {
                    if(item.isDeletable){
                        params.setMargins(textPaddingLeft, textPaddingTop, 2*textPaddingRight, texPaddingBottom)
                    }else{
                        params.setMargins(textPaddingLeft, textPaddingTop, textPaddingRight, texPaddingBottom)
                    }

                } else {
                    tv_tag_item_contain.visibility = View.GONE
                }

                tv_tag_item_contain.layoutParams = params
                tv_tag_item_contain.setTextColor(item.tagTextColor)
                tv_tag_item_contain.setTextSize(TypedValue.COMPLEX_UNIT_SP, item.tagTextSize)
                tagLayout.setOnClickListener {
                    if (mClickListener != null) {
                        mClickListener!!.onTagClick(position, item)
                    }
                }
                // calculate　of tag layout width
                var tagWidth = tv_tag_item_contain.paint.measureText(item.text)
                // tagView padding (left & right)
                if (!item.isOvalOfAdd && !item.isMoretable) {
                    tagWidth += (textPaddingLeft + textPaddingRight).toFloat()
                }

                // deletable text
                if (item.isDeletable) {
                    tv_tag_item_delete.visibility = View.VISIBLE
                    //deletableView.setText(tag.deleteIcon);
                    tv_tag_item_delete.setPadding(0, 0, size12, 0)

                    /*params = (LinearLayout.LayoutParams) deletableView.getLayoutParams();
                    params.setMargins(offset, textPaddingTop, textPaddingRight+offset, texPaddingBottom);
                    deletableView.setLayoutParams(params);*/

                    tv_tag_item_delete.setColorFilter(item.deleteIndicatorColor)
                    //deletableView.setTextSize(TypedValue.COMPLEX_UNIT_SP, tag.deleteIndicatorSize);
                    tv_tag_item_delete.setOnClickListener {
                        this@TagView.remove(position)
                        mDeleteListener?.onTagDeleted(position, item)
                    }
                    //tagWidth += deletableView.getMeasuredWidth() + deletableView.getPaddingLeft() + deletableView.getPaddingRight();
                    tagWidth += size22
                    // deletableView Padding (left & right)
                } else {
                    tv_tag_item_delete.visibility = View.GONE
                }

                val tagParams =
                        LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                //tagParams.setMargins(0, 0, 0, 0);
                //add margin of each line
                tagParams.bottomMargin = lineMargin
                if (mWidth <= (total + tagMargin + tagWidth + marginParent.toFloat()) && !scrollHorizonal && childCount > 0) {
                    //need to add in new line
                    tagParams.addRule(BELOW, index_bottom)
                    // initialize total param (layout padding left & layout padding right)
                    total = (paddingLeft + paddingRight).toFloat()
                    index_bottom = listIndex
                    index_header = listIndex
                } else {
                    //no need to new line
                    tagParams.addRule(ALIGN_TOP, index_header)
                    //not header of the line
                    if (listIndex != index_header) {
                        tagParams.addRule(RIGHT_OF, listIndex - 1)
                        tagParams.leftMargin = tagMargin
                        total += tagMargin.toFloat()
                        if (tag_pre!!.tagTextSize < item.tagTextSize) {
                            index_bottom = listIndex
                        }
                    }
                }
                total += tagWidth
                addView(tagLayout, tagParams)

                tag_pre = item
                listIndex++
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun getSelector(tag: Tag): Drawable {
        if (tag.background != null) return tag.background!!
        val states = StateListDrawable()
        val gdNormal = GradientDrawable()
        gdNormal.setColor(tag.layoutColor)
        gdNormal.cornerRadius = tag.radius
        if (tag.layoutBorderSize > 0) {
            gdNormal.setStroke(SizeUtil.dpToPx(context, tag.layoutBorderSize), tag.layoutBorderColor)
        }
        val gdPress = GradientDrawable()
        gdPress.setColor(tag.layoutColorPress)
        gdPress.cornerRadius = tag.radius
        states.addState(intArrayOf(android.R.attr.state_pressed), gdPress)
        states.addState(intArrayOf(android.R.attr.state_active), gdPress)
        //must add state_pressed first，or state_pressed will not take effect
        states.addState(intArrayOf(), gdNormal)
        return states
    }

    //chỉ dùng hàm này nếu add 1 tag thôi
    fun addTag(tag: Tag) {
        tags.add(tag)
        drawTags()
    }

    fun addTags(tags: Array<String>?) {
        if (tags == null || tags.isEmpty()) return
        for (item in tags) {
            val tag = Tag(item)
            this.tags.add(tag)
        }
        drawTags()
    }

    fun addTags(tagList: List<Tag>?) {
        if (tagList == null || tagList.size <= 0){
            tags.clear()
        }else{
            tags.addAll(tagList)
        }
        drawTags()
    }

    fun remove(position: Int) {
        tags.remove(position)
        drawTags()
    }

    fun removeAllTags() {
        tags.clear()
        drawTags()
    }

    fun setLineMargin(lineMargin: Float) {
        this.lineMargin = SizeUtil.dpToPx(context, lineMargin)
    }

    fun setTagMargin(tagMargin: Float) {
        this.tagMargin = SizeUtil.dpToPx(context, tagMargin)
    }

    fun setTextPaddingLeft(textPaddingLeft: Float) {
        this.textPaddingLeft = SizeUtil.dpToPx(context, textPaddingLeft)
    }

    fun setTextPaddingRight(textPaddingRight: Float) {
        this.textPaddingRight = SizeUtil.dpToPx(context, textPaddingRight)
    }

    fun setTextPaddingTop(textPaddingTop: Float) {
        this.textPaddingTop = SizeUtil.dpToPx(context, textPaddingTop)
    }

    fun setTexPaddingBottom(texPaddingBottom: Float) {
        this.texPaddingBottom = SizeUtil.dpToPx(context, texPaddingBottom)
    }

    fun setOnTagClickListener(clickListener: OnTagClickListener) {
        mClickListener = clickListener
    }

    fun setOnTagDeleteListener(deleteListener: OnTagDeleteListener) {
        mDeleteListener = deleteListener
    }

    fun setOnTagAddListener(addListener: OnTagAddListener) {
        mAddListener = addListener
    }

    companion object {
        val TAG = "TagView"
    }


}