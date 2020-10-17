package com.bilgiyon.pttemfilmuygulamasi.model.videos

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieVideoResults(
    @SerializedName("id")
    var id: String?,
    @SerializedName("kay")
    var key: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("site")
    var site: String?
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
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(site)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieVideoResults> {
        override fun createFromParcel(parcel: Parcel): MovieVideoResults {
            return MovieVideoResults(parcel)
        }

        override fun newArray(size: Int): Array<MovieVideoResults?> {
            return arrayOfNulls(size)
        }
    }
}