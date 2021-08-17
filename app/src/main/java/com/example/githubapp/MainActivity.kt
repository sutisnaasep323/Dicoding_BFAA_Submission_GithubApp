package com.example.githubapp

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUser: RecyclerView
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvUser = findViewById(R.id.rv_user)
        rvUser.setHasFixedSize(true)

        list.addAll(UserData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvUser.layoutManager = LinearLayoutManager(this)
        val listCityAdapter = UserAdapter(list)
        rvUser.adapter = listCityAdapter

        listCityAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User) {
        val intent = Intent(this@MainActivity, DetailUser::class.java)
        intent.putExtra(DetailUser.FOTO, user.avatar)
        intent.putExtra(DetailUser.USERNAME, user.username)
        intent.putExtra(DetailUser.NAME, user.name)
        intent.putExtra(DetailUser.LOKASI, user.location)
        startActivity(intent)
    }


}