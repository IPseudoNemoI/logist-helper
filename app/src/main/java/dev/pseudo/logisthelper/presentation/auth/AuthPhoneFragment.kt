package dev.pseudo.logisthelper.presentation.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.FragmentAuthPhoneBinding

class AuthPhoneFragment : Fragment() {

    private lateinit var binding: FragmentAuthPhoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthPhoneBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onTextChangedListener()
        redrawButtonAsState(false, binding.bNext)
        onBtnNextClick()

    }

    private fun onTextChangedListener() {
        binding.tPhone.addTextChangedListener {
            if (it != null) {
                redrawButtonAsState(it.length == 16, binding.bNext)
            }
        }
    }

    private fun redrawButtonAsState(state: Boolean, button: Button) {
        button.isEnabled = state
        if (!state) {
            button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.middle_gray_blue))
        } else {
            button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.alt_black))
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
    }

    private fun onBtnNextClick() {
        binding.bNext.setOnClickListener {

        }
    }
}