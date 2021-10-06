package com.example.githubapp.activity

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.data.DummyData
import com.example.githubapp.R
import com.example.githubapp.adapter.SearchAdapter
import com.example.githubapp.model.User
import com.example.githubapp.adapter.UserAdapter
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.model.GitItem
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvUser: RecyclerView
    private lateinit var rvSearch: RecyclerView

    //private lateinit var mainViewModel: MainViewModel
    private val list: ArrayList<User> = arrayListOf()
    private val list2: ArrayList<GitItem> = arrayListOf()

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "User's"

        rvUser = findViewById(R.id.recyclerView)
        rvUser.setHasFixedSize(true)

        list.addAll(DummyData.listData)
        showRecyclerList()

        rvSearch = findViewById(R.id.recyclerView)
        rvSearch.setHasFixedSize(true)
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
                Toast.makeText(this@MainActivity, query,Toast.LENGTH_LONG).show()
                showLoading(false)
                searchDataGithub(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun searchDataGithub(username: String) {
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://api.github.com/search/users?q=$username"
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                binding.progressBar.visibility = View.INVISIBLE

                val listGit = ArrayList<GitItem>()
                val result = String(responseBody)
                Log.d(TAG, result)

                try {
                    val responseArray = JSONObject(result)
                    val item = responseArray.getJSONArray("items")
                    for (i in 0 until item.length()) {
                        val i = item.getJSONObject(i)
                        val username = i.getString("login")
                        val avatar = i.getString("avatar_url")

                        val gitItem = GitItem()
                        gitItem.username = username
                        gitItem.avatar = avatar
                        listGit.add(gitItem)

                        showRecyclerListSearch()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun showRecyclerListSearch() {

        rvSearch.layoutManager = LinearLayoutManager(this)
        val listSearchUserAdapter = SearchAdapter(list2)
        rvSearch.adapter = listSearchUserAdapter

        listSearchUserAdapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GitItem) {
                Toast.makeText(this@MainActivity, "click", Toast.LENGTH_SHORT).show()
            }
        })
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