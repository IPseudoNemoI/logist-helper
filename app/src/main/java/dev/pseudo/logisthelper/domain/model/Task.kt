package dev.pseudo.logisthelper.domain.model

data class Task(
    val id: String,
    val price: String,
    val createdDate: String,
    val fromAddress: String,
    val fromDate: String,
    val toAddress: String,
    val toDate: String,
    val status: TaskStatus
)

enum class TaskStatus {
    NEW,
    PLANNED,
    IN_PROGRESS,
    CHECKING
}