package dev.pseudo.logisthelper.presentation.auth

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.FragmentAuthPhoneBinding

class AuthPhoneFragment : Fragment() {

    private lateinit var binding: FragmentAuthPhoneBinding
    private var isFormattingPhone = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        redrawButtonAsState(false, binding.bNext)
        setupPhoneMask()
        onBtnNextClick()
    }

    private fun setupPhoneMask() {
        binding.etPhone.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) = Unit

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) = Unit

            override fun afterTextChanged(s: Editable?) {
                if (isFormattingPhone) return

                isFormattingPhone = true

                val rawDigits = s.toString().filter { it.isDigit() }

                val phoneDigits = rawDigits
                    .removePrefix("7")
                    .removePrefix("8")
                    .take(10)

                val formatted = formatPhone(phoneDigits)

                binding.etPhone.setText(formatted)
                binding.etPhone.setSelection(formatted.length)

                redrawButtonAsState(phoneDigits.length == 10, binding.bNext)

                isFormattingPhone = false
            }
        })
    }

    private fun formatPhone(digits: String): String {
        return buildString {
            append("+7")

            if (digits.isNotEmpty()) {
                append(" ")
                append(digits.take(3))
            }

            if (digits.length > 3) {
                append(" ")
                append(digits.substring(3, minOf(6, digits.length)))
            }

            if (digits.length > 6) {
                append("-")
                append(digits.substring(6, minOf(8, digits.length)))
            }

            if (digits.length > 8) {
                append("-")
                append(digits.substring(8, minOf(10, digits.length)))
            }
        }
    }

    private fun redrawButtonAsState(state: Boolean, button: Button) {
        button.isEnabled = state

        if (!state) {
            button.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.gray)
            )
            button.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.middle_gray_blue)
            )
        } else {
            button.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.alt_black)
            )
            button.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.white)
            )
        }
    }

    private fun onBtnNextClick() {
        binding.bNext.setOnClickListener {

            savePhone(binding.etPhone.text.toString())

            findNavController().navigate(R.id.authPasswordFragment)
        }
    }

    private fun savePhone(phone: String) {
        requireContext()
            .getSharedPreferences("auth", Context.MODE_PRIVATE)
            .edit()
            .putString("phone", phone)
            .apply()
    }
}