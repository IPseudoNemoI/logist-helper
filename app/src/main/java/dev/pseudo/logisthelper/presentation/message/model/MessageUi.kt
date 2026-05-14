package dev.pseudo.logisthelper.presentation.message.model

data class MessageUi(
    val text: String,
    val time: String = "",
    val isMine: Boolean = false,
    val type: MessageType = MessageType.MESSAGE
)

enum class MessageType {
    DATE,
    MESSAGE
}