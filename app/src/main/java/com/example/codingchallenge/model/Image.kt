package com.example.codingchallenge.model

import android.os.Parcel
import android.os.Parcelable

data class Image(
    val id: Int,
    val user: String,
    val tags: String,
    val previewURL: String,
    val webformatURL: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(user)
        parcel.writeString(tags)
        parcel.writeString(previewURL)
        parcel.writeString(webformatURL)
        parcel.writeInt(likes)
        parcel.writeInt(downloads)
        parcel.writeInt(comments)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }
}
