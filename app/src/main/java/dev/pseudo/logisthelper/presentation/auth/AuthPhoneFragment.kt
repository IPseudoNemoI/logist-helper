package dev.pseudo.logisthelper.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.FragmentAuthPhoneBinding

class AuthPhoneFragment : Fragment() {
    lateinit var binding: FragmentAuthPhoneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthPhoneBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthPhoneFragment()

    }
}