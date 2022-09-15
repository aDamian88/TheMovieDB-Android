package com.adamian.themoviedb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adamian.themoviedb.data.network.model.MovieTvShow
import com.adamian.themoviedb.databinding.MovieItemBinding

class MovieTvShowAdapter(private val MovieTvShowList: List<MovieTvShow>) :

    RecyclerView.Adapter<MovieTvShowAdapter.ViewHolder>() {

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
        return MovieTvShowList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val displayItem = MovieTvShowList[position]
        holder.bindItem(displayItem)
    }

    inner class ViewHolder(val itemBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(movieTvShow: MovieTvShow) {
            itemBinding.tvTitle.text = movieTvShow.title
            itemBinding.tvReleaseDate.text = movieTvShow.releaseDate
        }
    }
}
