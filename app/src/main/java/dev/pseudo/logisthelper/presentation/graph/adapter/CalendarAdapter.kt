package dev.pseudo.logisthelper.presentation.graph.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.ItemCalendarDayBinding
import dev.pseudo.logisthelper.presentation.graph.model.CalendarDayUi
import dev.pseudo.logisthelper.presentation.graph.model.DayStatus

class CalendarAdapter(
    private val onDayClick: (CalendarDayUi) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private val items = mutableListOf<CalendarDayUi>()

    fun submitList(list: List<CalendarDayUi>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class CalendarViewHolder(
        private val binding: ItemCalendarDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CalendarDayUi) = with(binding) {
            tvDay.text = item.dayNumber

            val background = when (item.status) {
                DayStatus.WORK -> R.drawable.bg_day_work
                DayStatus.WEEKEND -> R.drawable.bg_day_holiday
                DayStatus.SICK -> R.drawable.bg_day_sick
                DayStatus.EXTRA_WORK -> R.drawable.bg_day_ready
            }

            tvDay.setBackgroundResource(background)

            if (item.isToday) {
                tvDay.foreground = ContextCompat.getDrawable(
                    root.context,
                    R.drawable.bg_day_today_outline
                )
            } else {
                tvDay.foreground = null
            }

            val textColor = if (item.isCurrentMonth) {
                R.color.alt_black
            } else {
                R.color.middle_gray_blue
            }

            tvDay.setTextColor(ContextCompat.getColor(root.context, textColor))

            root.setOnClickListener {
                if (item.isCurrentMonth) {
                    onDayClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemCalendarDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}