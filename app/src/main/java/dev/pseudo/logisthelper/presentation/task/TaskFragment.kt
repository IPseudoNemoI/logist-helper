package dev.pseudo.logisthelper.presentation.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.FragmentTaskBinding
import dev.pseudo.logisthelper.presentation.task.adapter.TaskAdapter

class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding: FragmentTaskBinding
        get() = _binding ?: error("FragmentTaskBinding is null")

    private val viewModel: TaskViewModel by activityViewModels()

    private val taskAdapter = TaskAdapter { task ->
        val bundle = Bundle().apply {
            putInt("id", task.id)
            putString("title", task.title)
            putString("price", task.price)
            putString("createdDate", task.createdDate)
            putString("fromAddress", task.fromAddress)
            putString("fromDate", task.fromDate)
            putString("toAddress", task.toAddress)
            putString("toDate", task.toDate)
            putString("statusText", task.statusText)
            putString("cargoType", task.cargoType)
            putString("bodyType", task.bodyType)
            putString("cargoWeight", task.cargoWeight)
            putString("contactName", task.contactName)
            putString("contactPhone", task.contactPhone)
            putString("statusText", task.statusText)
        }

        findNavController().navigate(
            R.id.action_taskFragment_to_taskDetailsFragment,
            bundle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        setupTabs()

        viewModel.loadIncomingTasks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvTasks.adapter = null
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.adapter = taskAdapter
    }

    private fun observeViewModel() {
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            taskAdapter.submitList(tasks)
        }
    }

    private fun setupTabs() {
        binding.tabLayoutTasks.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> viewModel.loadIncomingTasks()
                    1 -> viewModel.loadInProgressTasks()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) = Unit
            override fun onTabReselected(tab: TabLayout.Tab) = Unit
        })
    }
}