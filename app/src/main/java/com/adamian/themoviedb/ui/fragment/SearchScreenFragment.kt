package com.adamian.themoviedb.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adamian.themoviedb.R
import com.adamian.themoviedb.data.network.model.MovieTvShow
import com.adamian.themoviedb.databinding.FragmentSearchScreenBinding
import com.adamian.themoviedb.ui.TheMovieViewModel
import com.adamian.themoviedb.ui.adapter.MovieTvShowAdapter
import com.adamian.themoviedb.utils.Constants.MIN_SEARCH_CHARACTERS
import com.adamian.themoviedb.utils.Constants.SEARCH_DELAY
import com.adamian.themoviedb.utils.DataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchScreenFragment : Fragment(R.layout.fragment_search_screen) {

    private lateinit var binding: FragmentSearchScreenBinding
    private val viewModel: TheMovieViewModel by viewModels()
    private var recyclerAdapter: RecyclerView.Adapter<*>? = null
    private var currentDataSource = DataSource.API

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchScreenBinding.bind(view)
        binding.btSwitchResource.setOnClickListener {
            binding.etSearch.text.clear()
            viewModel.changeDataSource(currentDataSource)
        }
        setupRecyclerView();
        searchListener()
        observeViewModel()
    }

    private fun searchListener() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            var job: Job? = null
            override fun afterTextChanged(searchQuery: Editable?) {
                job?.cancel()
                job = MainScope().launch {
                    delay(SEARCH_DELAY)
                    searchQuery?.let {
                        if (searchQuery.length >= MIN_SEARCH_CHARACTERS ||
                            (searchQuery.isEmpty() && currentDataSource == DataSource.LOCAL)
                        ) {
                            viewModel.searchMoviesTvShowsBar(
                                searchQuery.toString(),
                                currentDataSource
                            )
                            displayResults()
                        } else {
                            if (currentDataSource == DataSource.API) {
                                emptyResults()
                            }
                        }
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvSearchMovie.layoutManager = LinearLayoutManager(activity)
    }

    private fun observeViewModel() {
        viewModel.searchMoviesTvShows.observe(viewLifecycleOwner) { response ->
            setButtonText(response.dataSource)
            setMovieTvShowList(response.results)
        }
    }

    private fun setMovieTvShowList(movieTvShowList: List<MovieTvShow>) {
        recyclerAdapter = MovieTvShowAdapter(context!!, movieTvShowList
            .filter { movieTvShow ->
                movieTvShow.mediaType == "movie" ||
                        movieTvShow.mediaType == "tv"
            })
        binding.rvSearchMovie.adapter = recyclerAdapter
        binding.tvResults.text =
            (recyclerAdapter as MovieTvShowAdapter).itemCount.toString() + " " + getText(R.string.results)

        (recyclerAdapter as MovieTvShowAdapter).setOnItemClickListener {
            val bundle = Bundle()
            bundle.putString("id", it.id.toString())
            bundle.putString("type", it.mediaType)
            findNavController().navigate(
                R.id.action_searchScreenFragment_to_detailsScreenFragment,
                bundle
            )
            binding.etSearch.text.clear()
        }
    }

    private fun setButtonText(dataSource: DataSource) {
        currentDataSource = dataSource
        if (dataSource == DataSource.LOCAL) {
            binding.btSwitchResource.text = getText(R.string.get_data_from_api)
            displayResults()
        } else {
            binding.btSwitchResource.text = getText(R.string.display_the_watchlist)
        }

    }

    private fun displayResults() {
        binding.llEmptyResults.visibility = View.GONE
        binding.llResults.visibility = View.VISIBLE
    }

    private fun emptyResults() {
        binding.llEmptyResults.visibility = View.VISIBLE
        binding.llResults.visibility = View.GONE
    }

}