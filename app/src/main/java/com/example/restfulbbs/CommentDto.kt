package com.example.restfulbbs

import android.os.Parcel
import android.os.Parcelable

data class CommentDto(val seq:Int, val id:String?, val content:String?, val wdate:String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(seq)
        parcel.writeString(id)
        parcel.writeString(content)
        parcel.writeString(wdate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommentDto> {
        override fun createFromParcel(parcel: Parcel): CommentDto {
            return CommentDto(parcel)
        }

        override fun newArray(size: Int): Array<CommentDto?> {
            return arrayOfNulls(size)
        }
    }

}