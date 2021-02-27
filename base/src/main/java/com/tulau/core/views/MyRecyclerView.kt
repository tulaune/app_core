package com.tulau.core.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tulau.core.Inf.MyCallBackLayout
import com.tulau.core.helper.MyRecyclerScroll
import com.tulau.core.utils.Constants

/**
 * Created by Tư Lầu on 2/18/20.
 */
class MyRecyclerView : RecyclerView {

    private var allowLoading: Boolean = false
    private var context = null
    var attrs: AttributeSet? = null
    var currentOffset = 0
    var listSize: Int = 0


    companion object {
        interface RecyclerViewLoaderInf {
            fun onLoadMore(offset: Int, limit: Int)
        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.attrs = attrs
    }


    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        //val a: Double = velocityY.toDouble() *= 1.3f
        // velocityX *= 0.7; for Horizontal recycler view. comment velocityY line not require for Horizontal Mode.
        return super.fling(velocityX, velocityY)
    }

    /**
     * list scroll xuống, gọi load more ở cuối list (60% của list sẽ được gọi loadmore)
     */
    fun setOnLoadDownMore(pendingToLoad: RecyclerViewLoaderInf) {
        this.addOnScrollListener(object : MyRecyclerScroll() {
            override fun show() {

            }

            override fun hide() {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val offset = recyclerView.computeVerticalScrollOffset()
                val extent = recyclerView.computeVerticalScrollExtent()
                val range = recyclerView.computeVerticalScrollRange()

                val percentageRCVScroll = (100.0 * offset / (range - extent).toFloat()).toInt()

                //Log.i("rcvListContentChat", "percentageRCVScroll: $percentageRCVScroll%")
                //Log.i("rcvListContentChat", "$percentageRCVScroll")

                if (percentageRCVScroll > 60 && listSize >= Constants.LIMIT) {      //đang kéo list lên từ 0% sẽ tăng dần đến 60% sẽ gọi loadmore
                    if (currentOffset != listSize) {
                        currentOffset = listSize
                        pendingToLoad.onLoadMore(listSize, Constants.LIMIT)
                        return
                    }

                } else {       //đang kéo list xuống
                    //Log.i("rcvListContentChat", "nó scroll xuống rồiiiii")
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

        })
    }

    /**
     * list scroll xuống, gọi load more ở cuối list (60% của list sẽ được gọi loadmore)
     */
    fun setOnLoadDownMore(limit: Int, pendingToLoad: RecyclerViewLoaderInf) {
        this.addOnScrollListener(object : MyRecyclerScroll() {
            override fun show() {

            }

            override fun hide() {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val offset = recyclerView.computeVerticalScrollOffset()
                val extent = recyclerView.computeVerticalScrollExtent()
                val range = recyclerView.computeVerticalScrollRange()

                val percentageRCVScroll = (100.0 * offset / (range - extent).toFloat()).toInt()

                //Log.i("rcvListContentChat", "percentageRCVScroll: $percentageRCVScroll%")
                //Log.i("rcvListContentChat", "$percentageRCVScroll")

                if (percentageRCVScroll > 60 && listSize >= limit) {      //đang kéo list lên từ 0% sẽ tăng dần đến 60% sẽ gọi loadmore
                    if (currentOffset != listSize) {
                        currentOffset = listSize
                        pendingToLoad.onLoadMore(listSize, limit)
                        return
                    }

                } else {       //đang kéo list xuống
                    //Log.i("rcvListContentChat", "nó scroll xuống rồiiiii")
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

        })
    }


    /**
     * list scroll ngược lên, gọi load more ở đầu list
     * vd: List Content Chat
     */
    fun setOnLoadUpMore(pendingToLoad: RecyclerViewLoaderInf) {
        this.addOnScrollListener(object : MyRecyclerScroll() {
            override fun show() {

            }

            override fun hide() {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val offset = recyclerView.computeVerticalScrollOffset()
                val extent = recyclerView.computeVerticalScrollExtent()
                val range = recyclerView.computeVerticalScrollRange()

                val percentageRCVScroll = (100.0 * offset / (range - extent).toFloat()).toInt()

                //Log.i("rcvListContentChat", "percentageRCVScroll: $percentageRCVScroll%")
                //Log.i("rcvListContentChat", "$percentageRCVScroll")

                if (percentageRCVScroll < 40 && listSize >= Constants.LIMIT) {  //đang kéo list xuống từ 100% sẽ giãm xuống đến 40%
                    if (currentOffset != listSize) {
                        currentOffset = listSize
                        pendingToLoad.onLoadMore(listSize, Constants.LIMIT)
                        return
                    }

                } else {                //đang kéo list lên
                    //Log.i("rcvListContentChat", "nó scroll lên rồiiiii")
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

        })
    }

    fun currentPositionVisibleListener(myCallBackLayout: MyCallBackLayout) {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val visibleItemCount: Int = recyclerView.layoutManager?.childCount ?: 0
                val firstVisibleItemPosition: Int = (recyclerView.layoutManager as LinearLayoutManager)?.findFirstVisibleItemPosition()
                val lastItem = firstVisibleItemPosition + visibleItemCount
                myCallBackLayout?.callBack(firstVisibleItemPosition, "CURRENT_POSITION_VISIBLE")
                myCallBackLayout?.callBack(lastItem, "LAST_CURRENT_POSITION_VISIBLE")

            }
        })
    }
}



