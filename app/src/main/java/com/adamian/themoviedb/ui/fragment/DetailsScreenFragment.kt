package com.adamian.themoviedb.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.adamian.themoviedb.R
import com.adamian.themoviedb.databinding.FragmentDetailsScreenBinding
import com.adamian.themoviedb.domain.model.MovieTvShowDisplay
import com.adamian.themoviedb.ui.TheMovieViewModel
import com.adamian.themoviedb.utils.Constants
import com.adamian.themoviedb.utils.Constants.getYoutubeVideoPath

import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsScreenFragment : Fragment(R.layout.fragment_details_screen) {

    private lateinit var binding: FragmentDetailsScreenBinding
    private val viewModel: TheMovieViewModel by viewModels()
    private var type = ""
    private lateinit var movieTvShowDisplay: MovieTvShowDisplay

    private val TAG = "DetailsScreenFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsScreenBinding.bind(view)
        val id = arguments?.getString("id")!!
        type = arguments?.getString("type")!!
        viewModel.getMovieTvShowDetails(id,type)
        observeViewModel()

        binding.ivArrowBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.ivDatabase.setOnClickListener {
            viewModel.onClickDatabaseButton(movieTvShowDisplay, type)
        }
    }

    private fun observeViewModel() {
        viewModel.getMovieTvShow.observe(viewLifecycleOwner) { response ->
            movieTvShowDisplay = response
            if (response.posterPath != null) {
                Glide.with(context!!).load(
                    Constants.getPosterPath(
                        response.posterPath
                    )
                ).into(binding.ivPoster)
            }
            binding.tvTitle.text = response.title
            binding.tvOverview.text = response.overview
            binding.tvGenre.text = response.genre
            setYoutubePlayer(response.trailerKey)
            setStoreIcon(response)
        }
    }

    private fun setYoutubePlayer(videoKey: String) {
        if (videoKey.isNotEmpty()) {
            binding.wvYoutube.settings.javaScriptEnabled = true
            binding.btPlay.setOnClickListener {
                binding.wvYoutube.loadUrl(getYoutubeVideoPath(videoKey))
            }
        } else {
            binding.btPlay.visibility = View.GONE
        }
    }

    private fun setStoreIcon(response: MovieTvShowDisplay) {
        if (response.isStored) {
            binding.ivDatabase.setImageResource(R.drawable.delete_icon)
        } else {
            binding.ivDatabase.setImageResource(R.drawable.save_icon)
        }
    }

}