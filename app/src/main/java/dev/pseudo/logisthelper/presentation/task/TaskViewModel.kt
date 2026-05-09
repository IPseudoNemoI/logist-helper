package dev.pseudo.logisthelper.presentation.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.pseudo.logisthelper.presentation.task.model.TaskUi

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<TaskUi>>()
    val tasks: LiveData<List<TaskUi>> = _tasks

    private val incomingTasks = mutableListOf<TaskUi>()
    private val inProgressTasks = mutableListOf<TaskUi>()

    init {
        incomingTasks.addAll(createIncomingTasks())
        inProgressTasks.addAll(createInProgressTasks())
    }

    fun loadIncomingTasks() {
        _tasks.value = incomingTasks.toList()
    }

    fun loadInProgressTasks() {
        _tasks.value = inProgressTasks.toList()
    }

    fun acceptTask(taskId: Int) {
        val task = incomingTasks.find { it.id == taskId } ?: return

        incomingTasks.remove(task)

        inProgressTasks.add(
            0,
            task.copy(statusText = "В процессе")
        )

        loadIncomingTasks()
    }

    fun declineTask(taskId: Int) {
        incomingTasks.removeAll { it.id == taskId }
        loadIncomingTasks()
    }

    fun completeTask(taskId: Int) {
        val index = inProgressTasks.indexOfFirst { it.id == taskId }
        if (index == -1) return

        val task = inProgressTasks[index]

        inProgressTasks[index] = task.copy(
            statusText = "Проверка"
        )

        loadInProgressTasks()
    }

    private fun createIncomingTasks(): List<TaskUi> {
        return listOf(
            TaskUi(
                id = 5,
                title = "Задание № 005",
                price = "27 000,00 ₽",
                createdDate = "05.05.2026 · 14:10",
                fromAddress = "улица Кирова, 86",
                fromDate = "06.05.2026 · 11:00",
                toAddress = "Ипподромская улица, 45",
                toDate = "06.05.2026 · 17:30",
                statusText = "Новое",
                cargoType = "Запчасти для спецтехники",
                bodyType = "Цельнометаллический фургон",
                cargoWeight = "850 кг",
                contactName = "Смирнов Алексей Викторович",
                contactPhone = "+7 923 145 62 18"
            ),
            TaskUi(
                id = 4,
                title = "Задание № 004",
                price = "51 200,00 ₽",
                createdDate = "05.05.2026 · 09:40",
                fromAddress = "улица Дуси Ковальчук, 270",
                fromDate = "06.05.2026 · 08:30",
                toAddress = "улица Станционная, 60",
                toDate = "06.05.2026 · 15:00",
                statusText = "Новое",
                cargoType = "Коробки с бытовой техникой",
                bodyType = "Фургон",
                cargoWeight = "1 450 кг",
                contactName = "Павлов Денис Сергеевич",
                contactPhone = "+7 913 912 40 55"
            ),
            TaskUi(
                id = 3,
                title = "Задание № 003",
                price = "30 000,00 ₽",
                createdDate = "05.05.2026 · 12:00",
                fromAddress = "Красный проспект, 25",
                fromDate = "06.05.2026 · 09:00",
                toAddress = "Советская улица, 18",
                toDate = "06.05.2026 · 14:00",
                statusText = "Новое",
                cargoType = "Документы и офисные коробки",
                bodyType = "Легковой фургон",
                cargoWeight = "180 кг",
                contactName = "Кузнецова Марина Олеговна",
                contactPhone = "+7 923 702 18 44"
            ),
            TaskUi(
                id = 2,
                title = "Задание № 002",
                price = "42 500,00 ₽",
                createdDate = "05.05.2026 · 10:30",
                fromAddress = "Геодезическая улица, 7",
                fromDate = "06.05.2026 · 08:00",
                toAddress = "улица Покрышкина, 3",
                toDate = "06.05.2026 · 16:00",
                statusText = "Новое",
                cargoType = "Мебельная фурнитура",
                bodyType = "Тентованный",
                cargoWeight = "1 100 кг",
                contactName = "Иванов Артём Николаевич",
                contactPhone = "+7 913 785 33 09"
            ),
            TaskUi(
                id = 1,
                title = "Задание № 001",
                price = "64 000,00 ₽",
                createdDate = "04.05.2026 · 18:20",
                fromAddress = "Фабричная улица, 10",
                fromDate = "05.05.2026 · 07:30",
                toAddress = "Военная улица, 12",
                toDate = "05.05.2026 · 13:30",
                statusText = "Новое",
                cargoType = "Продукты в заводской упаковке",
                bodyType = "Изотермический фургон",
                cargoWeight = "2 200 кг",
                contactName = "Морозова Елена Андреевна",
                contactPhone = "+7 923 188 64 27"
            )
        )
    }

    private fun createInProgressTasks(): List<TaskUi> {
        return listOf(
            TaskUi(
                id = 7,
                title = "Задание № 007",
                price = "54 300,00 ₽",
                createdDate = "06.05.2026 · 09:40",
                fromAddress = "улица Немировича-Данченко, 146",
                fromDate = "07.05.2026 · 07:00",
                toAddress = "улица Петухова, 79",
                toDate = "07.05.2026 · 15:30",
                statusText = "В процессе",
                cargoType = "Строительный инструмент",
                bodyType = "Бортовой",
                cargoWeight = "1 750 кг",
                contactName = "Соколов Илья Романович",
                contactPhone = "+7 913 440 81 36"
            ),
            TaskUi(
                id = 6,
                title = "Задание № 006",
                price = "91 000,00 ₽",
                createdDate = "06.05.2026 · 11:20",
                fromAddress = "Красный проспект, 220",
                fromDate = "07.05.2026 · 05:45",
                toAddress = "Толмачёвское шоссе, 19к3",
                toDate = "07.05.2026 · 13:40",
                statusText = "Проверка",
                cargoType = "Паллеты с расходными материалами",
                bodyType = "Евротент",
                cargoWeight = "3 000 кг",
                contactName = "Орлов Максим Андреевич",
                contactPhone = "+7 923 611 72 90"
            )
        )
    }
}