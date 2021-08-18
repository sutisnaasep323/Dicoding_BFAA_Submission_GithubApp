package com.example.githubapp

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var username: String? = "",
    var name: String? = "",
    var location: String? = "",
    var repository: Int = 0,
    var company: String? = "",
    var followers: Int = 0,
    var following: Int = 0,
    var avatar: Int = 0
): Parcelable
//{
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt()
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(username)
//        parcel.writeString(name)
//        parcel.writeString(location)
//        parcel.writeInt(repository)
//        parcel.writeString(company)
//        parcel.writeInt(followers)
//        parcel.writeInt(following)
//        parcel.writeInt(avatar)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<User> {
//        override fun createFromParcel(parcel: Parcel): User {
//            return User(parcel)
//        }
//
//        override fun newArray(size: Int): Array<User?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
