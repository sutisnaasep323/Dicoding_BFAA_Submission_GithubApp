package com.example.githubapp.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class GitItem(
    @JvmField
    var username: String? = "",
    var followers: Int? = 0,
    var following: Int? = 0,
    var avatar: String? = ""
) : Parcelable
