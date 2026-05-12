package dev.pseudo.logisthelper.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dev.pseudo.logisthelper.databinding.FragmentGraphBinding
import dev.pseudo.logisthelper.presentation.graph.GraphViewModel
import dev.pseudo.logisthelper.presentation.graph.adapter.CalendarAdapter
import dev.pseudo.logisthelper.presentation.graph.model.CalendarDayUi
import dev.pseudo.logisthelper.presentation.graph.model.DayStatus

class GraphFragment : Fragment() {

    private var _binding: FragmentGraphBinding? = null
    private val binding: FragmentGraphBinding
        get() = _binding ?: error("FragmentGraphBinding is null")

    private val viewModel: GraphViewModel by viewModels()

    private val calendarAdapter = CalendarAdapter { day ->
        showStatusDialog(day)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraphBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupCalendar()
        observeViewModel()
        setupMonthButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupCalendar() {
        binding.rvCalendar.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.rvCalendar.adapter = calendarAdapter
    }

    private fun observeViewModel() {
        viewModel.monthTitle.observe(viewLifecycleOwner) {
            binding.tvMonth.text = it
        }

        viewModel.days.observe(viewLifecycleOwner) {
            calendarAdapter.submitList(it)
        }
    }

    private fun setupMonthButtons() {
        binding.tvPrevMonth.setOnClickListener {
            viewModel.previousMonth()
        }

        binding.tvNextMonth.setOnClickListener {
            viewModel.nextMonth()
        }
    }

    private fun showStatusDialog(day: CalendarDayUi) {
        val options = arrayOf("Выходной", "Больничный")

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle(day.date.toString())
            .setItems(options) { _, which ->
                when (which) {
                    0 -> viewModel.setStatus(day.date, DayStatus.WEEKEND)
                    1 -> viewModel.setStatus(day.date, DayStatus.SICK)
                }
            }
            .show()
    }
}