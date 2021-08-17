package com.example.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailUser : AppCompatActivity() {

    companion object {
        const val FOTO = "foto"
        const val USERNAME = "username"
        const val NAME = "name"
        const val LOKASI = "lokasi"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val imgFotoReceived: ImageView = findViewById(R.id.img_rv_photo)
        val tvUserNameReceived: TextView = findViewById(R.id.tv_rv_username)
        val tvNameReceived: TextView = findViewById(R.id.tv_rv_name)
        val tvLocationReceived: TextView = findViewById(R.id.tv_rv_lokasi)

        val foto = intent.getIntExtra(FOTO, 0)
        val userName = intent.getStringExtra(USERNAME)
        val name = intent.getStringExtra(NAME)
        val lokasi = intent.getStringExtra(LOKASI)

        imgFotoReceived.setImageResource(foto)
        tvUserNameReceived.text = userName
        tvNameReceived.text = name
        tvLocationReceived.text = lokasi
    }
}