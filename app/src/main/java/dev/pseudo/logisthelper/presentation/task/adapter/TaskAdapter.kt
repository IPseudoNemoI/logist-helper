package dev.pseudo.logisthelper.presentation.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

        fun bind(item: TaskUi) {
            binding.tvTaskTitle.text = item.title
            binding.tvPrice.text = item.price
            binding.tvDate.text = item.createdDate

            binding.tvAddressFrom.text = item.fromAddress
            binding.tvDateFrom.text = item.fromDate

            binding.tvAddressTo.text = item.toAddress
            binding.tvDateTo.text = item.toDate

            binding.tvStatus.text = item.statusText
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

    override fun getItemCount() = items.size
}