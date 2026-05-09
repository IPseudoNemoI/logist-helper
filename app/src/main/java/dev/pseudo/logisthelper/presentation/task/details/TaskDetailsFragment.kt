package dev.pseudo.logisthelper.presentation.task.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.pseudo.logisthelper.databinding.FragmentTaskDetailsBinding
import dev.pseudo.logisthelper.presentation.main.MainActivity

class TaskDetailsFragment : Fragment() {

    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding: FragmentTaskDetailsBinding
        get() = _binding ?: error("FragmentTaskDetailsBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.hideBottomNav()

        setupToolbar()
        setupTaskInfo()
        setupButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as? MainActivity)?.showBottomNav()

        _binding = null
    }

    private fun setupToolbar() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupTaskInfo() = with(binding) {
        val title = arguments?.getString("title").orEmpty()
        val price = arguments?.getString("price").orEmpty()
        val createdDate = arguments?.getString("createdDate").orEmpty()
        val fromAddress = arguments?.getString("fromAddress").orEmpty()
        val fromDate = arguments?.getString("fromDate").orEmpty()
        val toAddress = arguments?.getString("toAddress").orEmpty()
        val toDate = arguments?.getString("toDate").orEmpty()
        val cargoType = arguments?.getString("cargoType").orEmpty()
        val bodyType = arguments?.getString("bodyType").orEmpty()
        val cargoWeight = arguments?.getString("cargoWeight").orEmpty()
        val contactName = arguments?.getString("contactName").orEmpty()
        val contactPhone = arguments?.getString("contactPhone").orEmpty()

        tvTaskTitle.text = title
        tvPrice.text = price
        tvCreatedDate.text = createdDate

        tvCargoType.text = cargoType
        tvBodyType.text = bodyType
        tvCargoWeight.text = cargoWeight

        tvContactName.text = contactName
        tvContactPhone.text = contactPhone

        tvFromAddress.text = "$fromAddress\n$fromDate"
        tvToAddress.text = "$toAddress\n$toDate"
    }

    private fun setupButtons() {
        binding.bAccept.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.bDecline.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}