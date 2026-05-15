package dev.pseudo.logisthelper.presentation.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.pseudo.logisthelper.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding ?: error("FragmentProfileBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPhone()
        setupLogout()
    }

    private fun setupPhone() {
        val phone = requireContext()
            .getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString("phone", null)

        binding.tvPhone.text = phone ?: "Номер не найден"
    }

    private fun setupLogout() {
        binding.tvLogout.setOnClickListener {
            requireContext()
                .getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}