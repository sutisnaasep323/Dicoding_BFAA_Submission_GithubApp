package com.example.githubapp.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.DummyData
import com.example.githubapp.R
import com.example.githubapp.adapter.OnItemClickCallback
import com.example.githubapp.adapter.SearchAdapter

import com.example.githubapp.model.User
import com.example.githubapp.adapter.UserAdapter
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.model.GitItem
import com.example.githubapp.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvUser: RecyclerView
    private lateinit var adapter: SearchAdapter
    private lateinit var mainViewModel: MainViewModel
    private val list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "User's"

        rvUser = findViewById(R.id.recyclerView)
        rvUser.setHasFixedSize(true)

        list.addAll(DummyData.listData)
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_here)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                Toast.makeText(this@MainActivity, query,Toast.LENGTH_LONG).show()
                showLoading(true)
                configMainViewModel()
                mainViewModel.setUser(query,this@MainActivity)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun showRecyclerList() {
        rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = UserAdapter(list)
        rvUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun configMainViewModel() {
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.getUser().observe(this, Observer {
            adapter.setData(it)
            configRecyclerView()
        })
    }

    private fun configRecyclerView() {
        adapter = SearchAdapter()
        adapter.notifyDataSetChanged()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.setOnItemClickCallback(object :
            OnItemClickCallback {
            override fun onIemClicked(userItems: GitItem) {
                showSelectedData(userItems)
            }
        })
    }

    private fun showSelectedData(userItems: GitItem) {

    }

    private fun showSelectedUser(user: User) {
        val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
        intent.putExtra(DetailUserActivity.USER, user)
        startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}