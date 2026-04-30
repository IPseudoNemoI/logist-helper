package dev.pseudo.logisthelper.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        binding.bottomNavigationView.setupWithNavController(controller)
    }

//    private fun replaceFragment() {
//        val controller = findNavController()
//
//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.task -> {
//                    controller.navigate(R.id.taskFragment)
//                    true
//                }
//
//                R.id.graph -> {
//                    controller.navigate(R.id.graphFragment)
//                    true
//                }
//
//                R.id.message -> {
//                    controller.navigate(R.id.messageFragment)
//                    true
//                }
//
//                R.id.profile -> {
//                    controller.navigate(R.id.profileFragment)
//                    true
//                }
//                else -> false
//            }
//        }
    }
