package com.example.githubapp.activity

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityUserDetailBinding
import com.example.githubapp.model.User

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
        val res: Resources = resources
        val dataUser = intent.getParcelableExtra<User>(USER) as User
        val data = String.format(res.getString(R.string.share_data), dataUser.name, dataUser.username, dataUser.company, dataUser.location, dataUser.repository, dataUser.followers, dataUser.following)

        when (item.itemId) {
            R.id.share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, data)
                startActivity(Intent.createChooser(intent, "Bagikan user ini ke"))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}