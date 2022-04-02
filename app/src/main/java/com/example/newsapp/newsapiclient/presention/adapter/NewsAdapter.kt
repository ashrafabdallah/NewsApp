package com.example.newsapp.newsapiclient.presention.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import com.example.newsapp.R
import com.example.newsapp.databinding.ListItemBinding
import com.example.newsapp.newsapiclient.data.model.Article

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }


    }
    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        var artical = differ.currentList[position]
        holder.bind(artical)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
        Log.i("MYTAGM", "came here ${differ.currentList.size}")
    }


    inner class NewViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvSource.text = article.source?.name
            binding.tvPublishedAt.text = article.publishedAt
            Glide.with(binding.ivArticleImage.context)
                .load(article.urlToImage)
                .into(binding.ivArticleImage)
            binding.root.setOnClickListener {
                onItemClickListner?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListner: ((Article) -> Unit)? = null
    fun setItemClickListner(listner: (Article) -> Unit) {
        onItemClickListner = listner
    }
}