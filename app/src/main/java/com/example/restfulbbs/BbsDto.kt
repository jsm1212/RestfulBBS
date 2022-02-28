package com.example.restfulbbs

import android.os.Parcel
import android.os.Parcelable

data class BbsDto(var seq:Int, var id:String?, var ref:Int, var step:Int, var depth:Int, var title:String?, var content:String?, var wdate:String?, var del:Int, var readcount:Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(seq)
        parcel.writeString(id)
        parcel.writeInt(ref)
        parcel.writeInt(step)
        parcel.writeInt(depth)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(wdate)
        parcel.writeInt(del)
        parcel.writeInt(readcount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BbsDto> {
        override fun createFromParcel(parcel: Parcel): BbsDto {
            return BbsDto(parcel)
        }

        override fun newArray(size: Int): Array<BbsDto?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return super.toString()
    }
}