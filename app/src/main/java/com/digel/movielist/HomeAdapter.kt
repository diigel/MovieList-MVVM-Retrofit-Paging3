package com.digel.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.digel.movielist.entity.Result
import kotlinx.android.synthetic.main.item_list_movie.view.*

class HomeAdapter : PagingDataAdapter<Result,HomeViewHolder>(HomeDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        )
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)holder.bind(item)
    }

    object HomeDiffUtil : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind( data : Result) = itemView.run {
        Glide.with(context).load(context.resources.getString(R.string.img_url) + data.posterPath).into(img_info)
        Glide.with(context).load(context.resources.getString(R.string.img_url) + data.backdropPath).into(img_background_path)
        txt_title.text = data.title
        txt_release.text = data.releaseDate
        txt_description.text = data.overview
    }
}