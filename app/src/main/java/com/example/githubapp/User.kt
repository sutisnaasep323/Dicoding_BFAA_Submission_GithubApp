package com.example.githubapp

data class User(
    var username: String = "",
    var name: String = "",
    var location: String = "",
    var repository: Int = 0,
    var company: String = "",
    var followers: Int = 0,
    var following: Int = 0,
    var avatar: Int = 0
)
