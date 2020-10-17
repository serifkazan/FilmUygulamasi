package com.bilgiyon.pttemfilmuygulamasi.model.reviews

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieReviewResult(
    @SerializedName("id")
    var id: String?,
    @SerializedName("author")
    var author: String?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("url")
    var url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieReviewResult> {
        override fun createFromParcel(parcel: Parcel): MovieReviewResult {
            return MovieReviewResult(parcel)
        }

        override fun newArray(size: Int): Array<MovieReviewResult?> {
            return arrayOfNulls(size)
        }
    }
}