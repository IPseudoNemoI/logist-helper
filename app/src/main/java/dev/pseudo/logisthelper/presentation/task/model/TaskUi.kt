package dev.pseudo.logisthelper.presentation.task.model

data class TaskUi(
    val title: String,
    val price: String,
    val createdDate: String,
    val fromAddress: String,
    val fromDate: String,
    val toAddress: String,
    val toDate: String,
    val statusText: String
)