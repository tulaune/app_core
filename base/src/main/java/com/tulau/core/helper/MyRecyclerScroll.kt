package com.tulau.core.helper

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Tư Lầu on 2/19/20.
 */
abstract class MyRecyclerScroll : RecyclerView.OnScrollListener() {

    var scrollDist = 0
    var isVisible = true


    abstract fun show()

    abstract fun hide()


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

    }
}