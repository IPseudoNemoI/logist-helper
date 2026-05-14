package dev.pseudo.logisthelper.presentation.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.pseudo.logisthelper.databinding.ItemMessageDateBinding
import dev.pseudo.logisthelper.databinding.ItemMessageMyBinding
import dev.pseudo.logisthelper.databinding.ItemMessageOtherBinding
import dev.pseudo.logisthelper.presentation.message.model.MessageType
import dev.pseudo.logisthelper.presentation.message.model.MessageUi

class MessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val messages = mutableListOf<MessageUi>()

    fun submitList(newMessages: List<MessageUi>) {
        messages.clear()
        messages.addAll(newMessages)
        notifyDataSetChanged()
    }

    fun addMessage(message: MessageUi) {
        messages.add(message)
        notifyItemInserted(messages.lastIndex)
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]

        return when (message.type) {
            MessageType.DATE -> VIEW_TYPE_DATE
            MessageType.MESSAGE -> {
                if (message.isMine) VIEW_TYPE_MY else VIEW_TYPE_OTHER
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_DATE -> {
                DateViewHolder(
                    ItemMessageDateBinding.inflate(inflater, parent, false)
                )
            }

            VIEW_TYPE_MY -> {
                MyViewHolder(
                    ItemMessageMyBinding.inflate(inflater, parent, false)
                )
            }

            else -> {
                OtherViewHolder(
                    ItemMessageOtherBinding.inflate(inflater, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val message = messages[position]

        when (holder) {
            is DateViewHolder -> holder.bind(message)
            is MyViewHolder -> holder.bind(message)
            is OtherViewHolder -> holder.bind(message)
        }
    }

    private class DateViewHolder(
        private val binding: ItemMessageDateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageUi) {
            binding.tvDate.text = message.text
        }
    }

    private class MyViewHolder(
        private val binding: ItemMessageMyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageUi) {
            binding.tvMessage.text = message.text
            binding.tvTime.text = message.time
        }
    }

    private class OtherViewHolder(
        private val binding: ItemMessageOtherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageUi) {
            binding.tvMessage.text = message.text
            binding.tvTime.text = message.time
        }
    }

    companion object {
        private const val VIEW_TYPE_DATE = 0
        private const val VIEW_TYPE_OTHER = 1
        private const val VIEW_TYPE_MY = 2
    }
}