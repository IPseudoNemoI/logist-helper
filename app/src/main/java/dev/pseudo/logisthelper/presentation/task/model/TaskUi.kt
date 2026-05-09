package dev.pseudo.logisthelper.presentation.task.model

data class TaskUi(
    val id: Int,
    val title: String,
    val price: String,
    val createdDate: String,
    val fromAddress: String,
    val fromDate: String,
    val toAddress: String,
    val toDate: String,
    val statusText: String,

    val cargoType: String,
    val bodyType: String,
    val cargoWeight: String,
    val contactName: String,
    val contactPhone: String
)