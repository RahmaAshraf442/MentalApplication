package com.example.mental_health_app.yoga_details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mental_health_app.databinding.FragmentYogaDetailsBinding
import com.example.mental_health_app.yoga.model.YogaModel
import com.example.notesapp.notes.viewmodel.YogaDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YogaDetailsFragment : Fragment() {
    private lateinit var binding: FragmentYogaDetailsBinding
    private  val viewModel: YogaDetailsViewModel by viewModels()
    var yogadetails :YogaModel?= null

    private fun setYoga(yoga: YogaModel) {
        this.yogadetails = yoga
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYogaDetailsBinding.inflate(inflater)
        var yogaId=arguments?.getInt("id")
        if(yogaId!=null){
            viewModel.getYogaDetails(yogaId)
            observeViewModel()
        }
        return binding.root
    }

     private fun observeViewModel(){
        viewModel.yoga.observe(viewLifecycleOwner) {
            setYoga(it)
            binding.tvYogaName.text = yogadetails?.yogaName
            binding.tvYogaDescription.text = yogadetails?.yogaDescription
            binding.ivYoga.setImageResource(yogadetails!!.yogaImage)
            binding.tvSteps.text = yogadetails?.steps
        }

     }
}