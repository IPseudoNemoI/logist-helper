package dev.pseudo.logisthelper.presentation.message

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.FragmentMessageBinding
import dev.pseudo.logisthelper.presentation.message.adapter.MessageAdapter
import dev.pseudo.logisthelper.presentation.message.model.MessageType
import dev.pseudo.logisthelper.presentation.message.model.MessageUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MessageFragment : Fragment(R.layout.fragment_message) {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageAdapter = MessageAdapter()

    private val viewModel: MessageViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMessageBinding.bind(view)

        setupRecyclerView()
        setupSendButton()
    }

    private fun setupRecyclerView() {
        binding.rvMessages.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
        }

        binding.rvMessages.adapter = messageAdapter
        messageAdapter.submitList(viewModel.messages)
    }

    private fun setupSendButton() {
        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString().trim()

            if (text.isEmpty()) return@setOnClickListener

            val message = MessageUi(
                text = text,
                time = getCurrentTime(),
                isMine = true
            )

            viewModel.addMessage(message)
            messageAdapter.addMessage(message)

            binding.etMessage.text.clear()
            binding.rvMessages.smoothScrollToPosition(viewModel.messages.lastIndex)
        }
    }

    private fun getCurrentTime(): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}