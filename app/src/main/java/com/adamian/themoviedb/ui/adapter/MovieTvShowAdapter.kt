package com.adamian.themoviedb.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adamian.themoviedb.data.network.model.MovieTvShow
import com.adamian.themoviedb.databinding.MovieItemBinding
import com.adamian.themoviedb.utils.Constants.getPosterPath
import com.bumptech.glide.Glide


class MovieTvShowAdapter(
    private val context: Context
) :

    RecyclerView.Adapter<MovieTvShowAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MovieTvShow>() {
        override fun areItemsTheSame(oldItem: MovieTvShow, newItem: MovieTvShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieTvShow, newItem: MovieTvShow): Boolean {
            return oldItem.equals(newItem)
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieTvShowAdapter.ViewHolder {
        return ViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val displayItem = differ.currentList[position]
        holder.bindItem(displayItem)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(displayItem) }
        }
    }

    private var onItemClickListener: ((MovieTvShow) -> Unit)? = null

    fun setOnItemClickListener(listener: (MovieTvShow) -> Unit) {
        onItemClickListener = listener
    }

    inner class ViewHolder(val itemBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(movieTvShow: MovieTvShow) {
            if (movieTvShow.mediaType == "movie") {
                itemBinding.tvTitle.text = movieTvShow.title
                itemBinding.tvReleaseDate.text = movieTvShow.releaseDate
            } else {
                itemBinding.tvTitle.text = movieTvShow.name
                itemBinding.tvReleaseDate.text = movieTvShow.firstAirDate
            }

            if (movieTvShow.posterPath != null) {
                Glide.with(context).load(getPosterPath(movieTvShow.posterPath))
                    .into(itemBinding.imMovieIcon)
            }
        }
    }
}
