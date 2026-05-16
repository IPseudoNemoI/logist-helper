package dev.pseudo.logisthelper.presentation.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.pseudo.logisthelper.databinding.BottomSheetDayStatusBinding
import dev.pseudo.logisthelper.databinding.FragmentGraphBinding
import dev.pseudo.logisthelper.presentation.graph.adapter.CalendarAdapter
import dev.pseudo.logisthelper.presentation.graph.model.CalendarDayUi
import dev.pseudo.logisthelper.presentation.graph.model.DayStatus
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

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

    override fun onResume() {
        super.onResume()
        viewModel.refreshCalendar()
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

    private fun formatDate(date: LocalDate): String {
        val month = date.month.getDisplayName(
            TextStyle.FULL_STANDALONE,
            Locale("ru")
        )

        return "${date.dayOfMonth} $month ${date.year}"
    }

    private fun showStatusDialog(day: CalendarDayUi) {
        val dialog = BottomSheetDialog(requireContext())
        val sheetBinding = BottomSheetDayStatusBinding.inflate(layoutInflater)

        sheetBinding.tvSelectedDate.text = formatDate(day.date)

        sheetBinding.tvWeekend.setOnClickListener {
            viewModel.setStatus(day.date, DayStatus.WEEKEND)
            dialog.dismiss()
        }

        sheetBinding.tvSick.setOnClickListener {
            viewModel.setStatus(day.date, DayStatus.SICK)
            dialog.dismiss()
        }

        sheetBinding.tvExtraWork.setOnClickListener {
            viewModel.setStatus(day.date, DayStatus.EXTRA_WORK)
            dialog.dismiss()
        }

        dialog.setContentView(sheetBinding.root)
        dialog.show()
    }
}