package dev.pseudo.logisthelper.presentation.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.pseudo.logisthelper.R
import dev.pseudo.logisthelper.databinding.ItemTaskBinding
import dev.pseudo.logisthelper.presentation.task.model.TaskUi

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val items = mutableListOf<TaskUi>()

    fun submitList(list: List<TaskUi>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TaskUi) = with(binding) {
            tvTaskTitle.text = item.title
            tvPrice.text = item.price
            tvDate.text = item.createdDate

            tvAddressFrom.text = item.fromAddress
            tvDateFrom.text = item.fromDate
            tvAddressTo.text = item.toAddress
            tvDateTo.text = item.toDate

            tvStatus.text = item.statusText
            applyStatusStyle(item.statusText)
        }

        private fun applyStatusStyle(status: String) = with(binding.tvStatus) {
            when (status) {
                "Новое" -> {
                    setBackgroundResource(R.drawable.bg_status_new)
                    setTextColor(ContextCompat.getColor(context, R.color.green))
                }

                "Запланировано" -> {
                    setBackgroundResource(R.drawable.bg_status_planned)
                    setTextColor(ContextCompat.getColor(context, R.color.blue))
                }

                "В процессе" -> {
                    setBackgroundResource(R.drawable.bg_status_progress)
                    setTextColor(ContextCompat.getColor(context, R.color.purple))
                }

                "Проверка" -> {
                    setBackgroundResource(R.drawable.bg_status_check)
                    setTextColor(ContextCompat.getColor(context, R.color.orange))
                }

                else -> {
                    setBackgroundResource(R.drawable.bg_status_new)
                    setTextColor(ContextCompat.getColor(context, R.color.green))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}