package com.tulau.core.views.my_simple_rating_bar

import android.os.Parcel
import android.os.Parcelable
import android.view.View

/**
 * Created by Tư Lầu on 2/28/18.
 */
class SavedState : View.BaseSavedState {
    var rating = 0f

    /**
     * Constructor called from [BaseRatingBar.onSaveInstanceState]
     */
    constructor(superState: Parcelable?) : super(superState) {}

    /**
     * Constructor called from [.CREATOR]
     */
    private constructor(`in`: Parcel) : super(`in`) {
        rating = `in`.readFloat()
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeFloat(rating)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SavedState?> = object : Parcelable.Creator<SavedState?> {
            override fun createFromParcel(`in`: Parcel): SavedState? {
                return SavedState(`in`)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }
    }
}