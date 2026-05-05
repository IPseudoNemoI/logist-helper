package dev.pseudo.logisthelper.presentation.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.pseudo.logisthelper.presentation.task.model.TaskUi

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<TaskUi>>()
    val tasks: LiveData<List<TaskUi>> = _tasks

    fun loadIncomingTasks() {
        _tasks.value = listOf(

            TaskUi(
                title = "Задание № 005",
                price = "27 000,00 ₽",
                createdDate = "05.05.2026 · 14:10",
                fromAddress = "улица Кирова, 86",
                fromDate = "06.05.2026 · 11:00",
                toAddress = "Ипподромская улица, 45",
                toDate = "06.05.2026 · 17:30",
                statusText = "Новое"
            ),

            TaskUi(
                title = "Задание № 004",
                price = "51 200,00 ₽",
                createdDate = "05.05.2026 · 09:40",
                fromAddress = "улица Дуси Ковальчук, 270",
                fromDate = "06.05.2026 · 08:30",
                toAddress = "улица Станционная, 60",
                toDate = "06.05.2026 · 15:00",
                statusText = "Новое"
            ),

            TaskUi(
                title = "Задание № 003",
                price = "30 000,00 ₽",
                createdDate = "05.05.2026 · 12:00",
                fromAddress = "Красный проспект, 25",
                fromDate = "06.05.2026 · 09:00",
                toAddress = "Советская улица, 18",
                toDate = "06.05.2026 · 14:00",
                statusText = "Новое"
            ),

            TaskUi(
                title = "Задание № 002",
                price = "42 500,00 ₽",
                createdDate = "05.05.2026 · 10:30",
                fromAddress = "Геодезическая улица, 7",
                fromDate = "06.05.2026 · 08:00",
                toAddress = "улица Покрышкина, 3",
                toDate = "06.05.2026 · 16:00",
                statusText = "Новое"
            ),

            TaskUi(
                title = "Задание № 001",
                price = "64 000,00 ₽",
                createdDate = "04.05.2026 · 18:20",
                fromAddress = "Фабричная улица, 10",
                fromDate = "05.05.2026 · 07:30",
                toAddress = "Военная улица, 12",
                toDate = "05.05.2026 · 13:30",
                statusText = "Новое"
            )
        )
    }
}