package machine.code.taskpal.presentation.ui.models

data class TaskModel(
    val title: String,
    val dueTime: String,
    val isCompleted: Boolean,
    val dataType: String
)
