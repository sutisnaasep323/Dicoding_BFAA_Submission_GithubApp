package com.example.githubapp.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.model.GitItem
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<GitItem>>()

    fun setUser(query: String, context: Context) {

        val listItems = ArrayList<GitItem>()

        val apiKey = "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"
        val url = "https://api.github.com/search/users?q=$query"

        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token $apiKey")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {

                    val result = String(responseBody!!)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("items")

                    for (i in 0 until list.length()) {
                        val users = list.getJSONObject(i)
                        val userItems = GitItem()

                        userItems.username = users.getString("login")
                        userItems.avatar = users.getString("avatar_url")

                        listItems.add(userItems)

                    }

                    listUsers.postValue(listItems)

                } catch (e: Exception) {
                    Toast.makeText(context, "Unable to Connect", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()

            }
        })

    }

    fun getUser(): LiveData<ArrayList<GitItem>> {
        return listUsers
    }

}