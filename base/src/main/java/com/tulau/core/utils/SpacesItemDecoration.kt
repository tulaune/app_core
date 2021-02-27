package com.tulau.core.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Tư Lầu on 2019-04-27.
 */


class SpacesItemDecoration(
        private val mSpace: Int, spaceHeader: Boolean, private val typeLayout: Int
) : RecyclerView.ItemDecoration() {
    private var spaceHeader = true

    companion object {
        const val STAGEGRID = 1
        const val GRID = 2
        const val LINEAR = 3        //add space for 4 directions
        const val HORIZONTAL = 4
        const val LINEAR_ONTOPBOTTOM = 5        //add space only for top & bottom item
        const val HORIZONTAL_FOR_FILTER = 6        //add space for tab filter
    }

    init {
        this.spaceHeader = spaceHeader
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        when (typeLayout) {
            HORIZONTAL_FOR_FILTER -> {
                outRect.left = mSpace / 2
                outRect.right = mSpace / 2
                outRect.bottom = mSpace
                outRect.top = mSpace
            }

            HORIZONTAL -> {
                outRect.left = mSpace / 2
                outRect.right = mSpace/ 2
                outRect.top = mSpace
                outRect.bottom = mSpace


//                if (spaceHeader && parent.getChildAdapterPosition(view) == 0) {
//                    outRect.left = 0
//                    outRect.right = 0
//                    outRect.bottom = mSpace
//                    outRect.top = 0
//                }
            }

            LINEAR -> {
                outRect.left = mSpace
                outRect.right = mSpace
                outRect.bottom = mSpace / 2
                outRect.top = mSpace / 2

                if (spaceHeader && parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = 0
                    outRect.right = 0
                    outRect.bottom = mSpace
                    outRect.top = 0
                }
            }

            LINEAR_ONTOPBOTTOM -> {
                outRect.left = 0
                outRect.right = 0
                outRect.bottom = mSpace / 2
                outRect.top = mSpace / 2

                if (spaceHeader && parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = 0
                    outRect.right = 0
                    outRect.bottom = mSpace / 2
                    outRect.top = mSpace / 2
                }
            }

            GRID -> {
                outRect.left = mSpace
                outRect.right = mSpace
                outRect.bottom = mSpace
                outRect.top = mSpace

                if (spaceHeader && parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = 0
                    outRect.right = 0
                    outRect.bottom = mSpace
                    outRect.top = 0
                }
            }

            else -> {
                outRect.bottom = mSpace
                outRect.top = mSpace
                outRect.left = mSpace
                outRect.right = mSpace

                if (spaceHeader && parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = 0
                    outRect.right = 0
                    outRect.bottom = mSpace
                    outRect.top = mSpace * 3
                }
            }
        }
    }
}