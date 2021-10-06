package com.example.githubapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubapp.R
import com.example.githubapp.model.GitItem

class SearchAdapter (private val listGit: ArrayList<GitItem>): RecyclerView.Adapter<SearchAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_search, viewGroup, false)
        return ListViewHolder(view)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUserName: TextView = itemView.findViewById(R.id.tv_search_item_username)
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_search_item_photo)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ListViewHolder, position: Int) {
        val user = listGit[position]
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(86, 86))
            .into(holder.imgAvatar)
        holder.tvUserName.text = user.username

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listGit[holder.absoluteAdapterPosition])
        }

    }

    override fun getItemCount(): Int {
        return listGit.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GitItem)
    }
}