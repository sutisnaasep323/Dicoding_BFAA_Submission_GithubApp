package com.example.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailUser : AppCompatActivity() {

    companion object {
        const val USER = "user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val imgFotoReceived: ImageView = findViewById(R.id.img_rv_photo)
        val tvUserNameReceived: TextView = findViewById(R.id.tv_rv_username)
        val tvNameReceived: TextView = findViewById(R.id.tv_rv_name)
        val tvLocationReceived: TextView = findViewById(R.id.tv_rv_lokasi)
        val tvFollowersReceived: TextView = findViewById(R.id.tv_rv_followers)
        val tvFollowingReceived: TextView = findViewById(R.id.tv_rv_following)
        val tvCompanyReceived: TextView = findViewById(R.id.tv_rv_company)
        val tvRepositoryReceived: TextView = findViewById(R.id.tv_rv_repository)

        val dataUser = intent.getParcelableExtra<User>(USER) as User
        imgFotoReceived.setImageResource(dataUser.avatar)
        tvUserNameReceived.text = dataUser.username
        tvNameReceived.text = dataUser.name
        tvLocationReceived.text = dataUser.location
        tvFollowersReceived.text = dataUser.followers.toString()
        tvFollowingReceived.text = dataUser.following.toString()
        tvCompanyReceived.text = dataUser.company
        tvRepositoryReceived.text = dataUser.repository.toString()

    }
}