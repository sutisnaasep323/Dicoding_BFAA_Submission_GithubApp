package com.example.githubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        val dataUser = intent.getParcelableExtra<User>(USER) as User
        binding.imgRvPhoto.setImageResource(dataUser.avatar)
        binding.tvRvUsername.text = dataUser.username
        binding.tvRvName.text = dataUser.name
        binding.tvRvLokasi.text = dataUser.location
        binding.tvRvCompany.text = dataUser.company
        binding.tvRvFollowers.text = dataUser.followers.toString()
        binding.tvRvFollowing.text = dataUser.following.toString()
        binding.tvRvRepository.text = dataUser.repository.toString()

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
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, dataUser.name)
                startActivity(Intent.createChooser(intent, "Bagikan user ini ke"))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}