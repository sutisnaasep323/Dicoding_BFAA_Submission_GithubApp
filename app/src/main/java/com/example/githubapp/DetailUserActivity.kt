package com.example.githubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.githubapp.databinding.ActivityUserDetailBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    companion object {
        const val USER = "user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val imgFotoReceived: ImageView = findViewById(R.id.img_rv_photo)
//        val tvUserNameReceived: TextView = findViewById(R.id.tv_rv_username)
//        val tvNameReceived: TextView = findViewById(R.id.tv_rv_name)
//        val tvLocationReceived: TextView = findViewById(R.id.tv_rv_lokasi)
//        val tvFollowersReceived: TextView = findViewById(R.id.tv_rv_followers)
//        val tvFollowingReceived: TextView = findViewById(R.id.tv_rv_following)
//        val tvCompanyReceived: TextView = findViewById(R.id.tv_rv_company)
//        val tvRepositoryReceived: TextView = findViewById(R.id.tv_rv_repository)

        val dataUser = intent.getParcelableExtra<User>(USER) as User
        binding.imgRvPhoto.setImageResource(dataUser.avatar)
        binding.tvRvUsername.text = dataUser.username
        binding.tvRvName.text = dataUser.name
        binding.tvRvLokasi.text = dataUser.location
        binding.tvRvCompany.text = dataUser.company
        binding.tvRvFollowers.text = dataUser.followers.toString()
        binding.tvRvFollowing.text = dataUser.following.toString()
        binding.tvRvRepository.text = dataUser.repository.toString()

//        imgFotoReceived.setImageResource(dataUser.avatar)
//        tvUserNameReceived.text = dataUser.username
//        tvNameReceived.text = dataUser.name
//        tvLocationReceived.text = dataUser.location
//        tvFollowersReceived.text = dataUser.followers.toString()
//        tvFollowingReceived.text = dataUser.following.toString()
//        tvCompanyReceived.text = dataUser.company
//        tvRepositoryReceived.text = dataUser.repository.toString()

        supportActionBar?.title = dataUser.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dataUser = intent.getParcelableExtra<User>(USER) as User

        when (item.itemId) {
            R.id.share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT, dataUser.name)
//                intent.putExtra(Intent.EXTRA_TEXT, dataUser.username)
//                intent.putExtra(Intent.EXTRA_TEXT, dataUser.company)
//                intent.putExtra(Intent.EXTRA_TEXT, dataUser.location)
                startActivity(Intent.createChooser(intent, "Bagikan user ini ke"))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}