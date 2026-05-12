package dev.pseudo.logisthelper.presentation.graph.model

import java.time.LocalDate

data class CalendarDayUi(
    val date: LocalDate,
    val dayNumber: String,
    val isCurrentMonth: Boolean,
    val isToday: Boolean,
    val status: DayStatus
)

enum class DayStatus {
    WORK,
    WEEKEND,
    SICK,
    EXTRA_WORK
}