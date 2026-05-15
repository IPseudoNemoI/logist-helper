package dev.pseudo.logisthelper.presentation.message

import androidx.lifecycle.ViewModel
import dev.pseudo.logisthelper.presentation.message.model.MessageType
import dev.pseudo.logisthelper.presentation.message.model.MessageUi

class MessageViewModel : ViewModel() {

    private val _messages = mutableListOf(
        MessageUi(
            text = "14 мая",
            type = MessageType.DATE
        ),
        MessageUi(
            text = "Ты сегодня выйдешь на смену?",
            time = "7:48",
            isMine = false
        ),
        MessageUi(
            text = "Да, сегодня буду",
            time = "7:48",
            isMine = true
        ),
        MessageUi(
            text = "Ждем тебя",
            time = "7:49",
            isMine = false
        )
    )

    val messages: List<MessageUi>
        get() = _messages

    fun addMessage(message: MessageUi) {
        _messages.add(message)
    }
}