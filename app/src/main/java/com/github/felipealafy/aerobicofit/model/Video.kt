package com.github.felipealafy.aerobicofit.model

import android.os.Parcel
import android.os.Parcelable

data class Video(var title: String, var link: String, var desc: String, var limitSets: Int, var setTime: Long, var restTime: Long): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(link)
        parcel.writeString(desc)
        parcel.writeInt(limitSets)
        parcel.writeLong(setTime)
        parcel.writeLong(restTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }
    }
}