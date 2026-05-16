package dev.pseudo.logisthelper.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.otpview.OTPListener
import com.otpview.OTPTextView
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.FragmentAuthPasswordBinding

class AuthPasswordFragment : Fragment() {

    private lateinit var otpTextView: OTPTextView
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
        otpTextView = binding.etPassword
        otpTextView.requestFocusOTP()
        backToLastFragment()
        onTextChangedListener()
        redrawButtonAsState(false, binding.bNext)
        checkPassword()
    }

    private fun checkPassword() {
        val truePass = "111111"

        binding.bNext.setOnClickListener {
            val password = binding.etPassword.otp.orEmpty()

            if (password == truePass) {
                findNavController().navigate(R.id.mainActivity)
            } else {
                drawError()
            }
        }
    }

//    private fun redrawBorder() {
//        binding.textInputLayoutPass.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
//    }

    private fun drawError() {
        binding.tvError.text = "Неверный пароль"
    }

    private fun backToLastFragment() {
        val controller = findNavController()
        binding.ivArrow.setOnClickListener {
            controller.navigate(R.id.authPhoneFragment)
        }
    }

    private fun onTextChangedListener() {
        otpTextView.otpListener = object : OTPListener {

            override fun onInteractionListener() {
                if (binding.etPassword.otp != null) {
                    redrawButtonAsState(
                        binding.etPassword.otp.toString().length == 6,
                        binding.bNext
                    )
                }
            }

            override fun onOTPComplete(otp: String) {

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

}
