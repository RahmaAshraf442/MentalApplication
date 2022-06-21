package com.example.mental_health_app.recommender.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mental_health_app.databinding.FragmentRecommenderBinding
import com.example.mental_health_app.recommender.view.adapter.RecommenderAdapter
import com.example.notesapp.notes.viewmodel.RecommenderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommenderFragment : Fragment() {

    private val viewModel: RecommenderViewModel by viewModels()
    private lateinit var binding: FragmentRecommenderBinding
    private lateinit var adapter : RecommenderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommenderBinding.inflate(inflater)
        adapter= RecommenderAdapter()
        observeRecommendedViewModel()
        binding.rvBooks.layoutManager= LinearLayoutManager(context)
        binding.rvBooks.adapter=adapter
        onClickActions()
        return binding.root
    }

    private fun onClickActions(){
        binding.llRecommended.setOnClickListener{
            observeRecommendedViewModel()
        }

        binding.llBest.setOnClickListener{
            observeBestViewModel()
        }
        binding.llRandom.setOnClickListener{
            observeRandomViewModel()
        }

        binding.ivSearch.setOnClickListener{
            val searchInput = binding.etSearch.text.toString()
            observeSearchViewModel(searchInput)
        }
    }

    private fun observeRecommendedViewModel() {
        viewModel.getAllRecommended()
        viewModel.allRecommended.observe(viewLifecycleOwner) {
            adapter.setBooks(it)
        }
    }

    private fun observeBestViewModel() {
        viewModel.getAllBest()
        viewModel.allBest.observe(viewLifecycleOwner) {
            adapter.setBooks(it)
        }
    }

    private fun observeRandomViewModel() {
        viewModel.getAllRandom()
        viewModel.allRandom.observe(viewLifecycleOwner) {
            adapter.setBooks(it)
        }
    }

    private fun observeSearchViewModel(searchInput: String) {
        viewModel.getAllSearch(searchInput)
        viewModel.allSearch.observe(viewLifecycleOwner) {
            adapter.setBooks(it)
        }
    }


}