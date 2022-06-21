package com.example.mental_health_app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mental_health_app.chat.view.ChatActivity
import com.example.mental_health_app.databinding.ActivityMainBinding
import com.example.mental_health_app.databinding.FragmentHomeBinding
import com.example.notesapp.notes.view.NotesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        onClickActions()
        return binding.root
    }

    private fun onClickActions() {
        binding.llNotes.setOnClickListener(){
            val intent = Intent (activity, NotesActivity::class.java)
            activity?.startActivity(intent)
        }

        binding.llJournal.setOnClickListener{view ->
            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_journal_thoughts)

        }

        binding.llQuotes.setOnClickListener(){
            val intent = Intent (activity, QuotesActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}