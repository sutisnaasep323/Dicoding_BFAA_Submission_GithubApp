package com.example.githubapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.databinding.ItemSearchBinding
import com.example.githubapp.model.GitItem

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private val mData = ArrayList<GitItem>()
    private var onItemClickCallback: OnItemClickCallback? = null
    private lateinit var binding: ItemSearchBinding

    fun setData(item: ArrayList<GitItem>) {
        mData.clear()
        mData.addAll(item)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        )
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userItems: GitItem) {
            with(itemView) {

                binding.tvSearchItemUsername.text = userItems.username
//                binding.type.text = userItems.type
                Glide.with(itemView.context)
                    .load(userItems.avatar)
                    .into(binding.imgSearchItemPhoto)
                itemView.setOnClickListener { onItemClickCallback?.onIemClicked(userItems) }


            }
        }
    }
}

interface OnItemClickCallback {
    fun onIemClicked(userItems: GitItem)
}