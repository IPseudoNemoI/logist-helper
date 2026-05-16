package dev.pseudo.logisthelper.presentation.graph

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.pseudo.logisthelper.presentation.graph.model.CalendarDayUi
import dev.pseudo.logisthelper.presentation.graph.model.DayStatus
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class GraphViewModel : ViewModel() {

    private val _days = MutableLiveData<List<CalendarDayUi>>()
    val days: LiveData<List<CalendarDayUi>> = _days

    private val _monthTitle = MutableLiveData<String>()
    val monthTitle: LiveData<String> = _monthTitle

    private var currentMonth: YearMonth = YearMonth.now()
    private val customStatuses = mutableMapOf<LocalDate, DayStatus>()

    init {
        loadMonth()
    }

    fun nextMonth() {
        currentMonth = currentMonth.plusMonths(1)
        loadMonth()
    }

    fun previousMonth() {
        currentMonth = currentMonth.minusMonths(1)
        loadMonth()
    }

    fun setStatus(date: LocalDate, status: DayStatus) {
        customStatuses[date] = status
        loadMonth()
    }

    private fun loadMonth() {
        _monthTitle.value = buildMonthTitle(currentMonth)

        val firstDay = currentMonth.atDay(1)
        val firstGridDay = firstDay.minusDays((firstDay.dayOfWeek.value - 1).toLong())

        val days = (0 until 42).map { index ->
            val date = firstGridDay.plusDays(index.toLong())

            CalendarDayUi(
                date = date,
                dayNumber = date.dayOfMonth.toString(),
                isCurrentMonth = date.month == currentMonth.month,
                isToday = date == LocalDate.now(),
                status = customStatuses[date] ?: getDefaultStatus(date)
            )
        }

        _days.value = days
    }

    private fun getDefaultStatus(date: LocalDate): DayStatus {
        val startDate = LocalDate.of(2026, 5, 1)

        val daysFromStart = java.time.temporal.ChronoUnit.DAYS.between(startDate, date).toInt()
        val cycleDay = Math.floorMod(daysFromStart, 4)

        return when (cycleDay) {
            0, 1 -> DayStatus.WORK
            else -> DayStatus.WEEKEND
        }
    }

    private fun buildMonthTitle(yearMonth: YearMonth): String {
        val month = yearMonth.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale("ru"))
        return "${month.replaceFirstChar { it.uppercase() }} ${yearMonth.year}"
    }

    fun refreshCalendar() {
        loadMonth()
    }
}