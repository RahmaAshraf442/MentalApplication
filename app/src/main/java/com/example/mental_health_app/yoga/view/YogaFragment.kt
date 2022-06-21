package com.example.mental_health_app.yoga.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mental_health_app.R
import com.example.mental_health_app.databinding.FragmentYogaBinding
import com.example.mental_health_app.yoga.model.YogaModel
import com.example.mental_health_app.yoga.view.adapter.YogaAdapter
import com.example.notesapp.notes.viewmodel.YogaViewModel
import com.example.paytabs_demo_store_android.onboarding.config.AppPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class YogaFragment : Fragment() {

    private val viewModel: YogaViewModel by viewModels()
    private lateinit var binding: FragmentYogaBinding
    private lateinit var adapter :YogaAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYogaBinding.inflate(inflater)
        viewModel.getAllYoga()
        adapter= YogaAdapter { itemClicked(it) }
        binding.rvYoga.layoutManager=LinearLayoutManager(context)
        binding.rvYoga.adapter=adapter
        observeViewModel()
        return binding.root

    }

    private fun itemClicked(id: Int){
        findNavController().navigate(
            R.id.action_navigation_yoga_to_navigation_yoga_details,
            bundleOf("id" to id)
        )
    }

    private fun observeViewModel() {
        viewModel.allYoga.observe(viewLifecycleOwner) {
            adapter.setYoga(it)
        }
    }

}