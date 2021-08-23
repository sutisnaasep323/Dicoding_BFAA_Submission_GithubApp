package com.example.githubapp.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class User(
    @JvmField
    var username: String? = "",
    var name: String? = "",
    var location: String? = "",
    var repository: Int? = 0,
    var company: String? = "",
    var followers: Int? = 0,
    var following: Int? = 0,
    var avatar: Int = 0
) : Parcelable
