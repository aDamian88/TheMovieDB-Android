package com.adamian.themoviedb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamian.themoviedb.R
import com.adamian.themoviedb.databinding.ActivityMainBinding
import com.adamian.themoviedb.databinding.FragmentDetailsScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsScreenFragment : Fragment(R.layout.fragment_details_screen) {

    private lateinit var binding: FragmentDetailsScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsScreenBinding.bind(view)
    }

}