package com.example.mental_health_app.journal.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mental_health_app.databinding.FragmentJournalThoughtsBinding
import com.example.mental_health_app.journal.model.JournalImagesModel
import com.example.notesapp.notes.view.JournalGVAdapter
import com.example.notesapp.notes.viewmodel.JournalImagesViewModel
import com.example.notesapp.notes.viewmodel.JournalViewredImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JournalThoughtsFragment : Fragment() {
    private lateinit var binding: FragmentJournalThoughtsBinding
    private  val viewModel: JournalImagesViewModel by viewModels()
    private  val viewedViewModel: JournalViewredImagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJournalThoughtsBinding.inflate(inflater)
        viewModel.getAllJournalImage()
        observeJournalImagesViewModel()
        return binding.root
    }


    private fun observeJournalImagesViewModel() {
        viewModel.allJournalImages.observe(viewLifecycleOwner) {
            if (it.toList().isNotEmpty()){
                val image = it.toList().random()
                observeViewedImagesViewModel()
                getRandomImage(image)
                return@observe
            }
            updateDatabase()
        }
    }

    private fun getRandomImage(image: JournalImagesModel){
        Log.e("TAG",image.toString())
        binding.ivTesting.setImageResource(image.journalImage)
        viewedViewModel.addViewedJournalImage(image)
        viewModel.deleteJournalImage(image)
    }

    private fun observeViewedImagesViewModel(){
        viewedViewModel.getAllViewedJournalImage()
        viewedViewModel.allViewedJournalImages.observe(viewLifecycleOwner){
            binding.gvViewedImages.adapter =JournalGVAdapter(it.toList() , requireContext())
            Log.e("ViewedImagesLog", it.toList().toString())
        }
    }


    private fun updateDatabase(){
        viewedViewModel.getAllViewedJournalImage()
        viewedViewModel.allViewedJournalImages.observe(viewLifecycleOwner){
            for (image in it.toList()){
                viewModel.addJournalImage(image)
            }
            val imageJournal = it.toList().random()
            viewedViewModel.deleteAllViewedJournalImage()
            getRandomImage(imageJournal)
        }
    }

}