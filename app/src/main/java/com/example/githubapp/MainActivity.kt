package com.example.githubapp

    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
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
        val listUserAdapter = UserAdapter(list)
        rvUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User) {
        val intent = Intent(this@MainActivity, DetailUser::class.java)
        intent.putExtra(DetailUser.USER, user)
        startActivity(intent)
    }


}