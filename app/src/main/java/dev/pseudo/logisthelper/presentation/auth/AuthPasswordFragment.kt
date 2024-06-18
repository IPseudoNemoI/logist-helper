package dev.pseudo.logisthelper.presentation.auth

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.FragmentAuthPasswordBinding

class AuthPasswordFragment : Fragment() {

    private lateinit var binding: FragmentAuthPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthPasswordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backToLastFragment()
        onTextChangedListener()
        redrawButtonAsState(false, binding.bNext)
        checkPassword()
    }

    private fun checkPassword() {
        val truePass = 111111
        binding.bNext.setOnClickListener {
            val password = binding.etPassword.text.toString()
            if (password == truePass.toString()) {
                Log.d("damn", "Агагаг")
            } else {
                Log.d("damn", "Неа")
                redrawBorder()
                drawError()
            }
        }
    }

    private fun redrawBorder() {
        binding.textInputLayoutPass.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
    }

    private fun drawError() {
        binding.tvError.text = "Неверный пароль"
    }

    private fun backToLastFragment() {
        val conroller = findNavController()
        binding.ivArrow.setOnClickListener {
            conroller.navigate(R.id.authPhoneFragment)
        }
    }

    private fun onTextChangedListener() {
        binding.etPassword.addTextChangedListener {
            if (it != null) {
                redrawButtonAsState(it.length == 6, binding.bNext)
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
        val controller = findNavController()
        binding.bNext.setOnClickListener {

        }
    }
}
