package com.adamian.themoviedb.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.adamian.themoviedb.R
import com.adamian.themoviedb.databinding.FragmentSearchScreenBinding
import com.adamian.themoviedb.ui.TheMovieViewModel
import com.adamian.themoviedb.utils.Constants.MIN_SEARCH_CHARACTERS
import com.adamian.themoviedb.utils.Constants.SEARCH_DELAY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchScreenFragment : Fragment(R.layout.fragment_search_screen) {

    private lateinit var binding: FragmentSearchScreenBinding
    private val viewModel: TheMovieViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchScreenBinding.bind(view)
        searchListener()

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
                        if (searchQuery.length >= MIN_SEARCH_CHARACTERS) {
                            viewModel.searchMovies(searchQuery.toString())
                            // displayResults()
                        } else {
                            // emptyResults()
                        }
                    }
                }
            }
        })
    }

}