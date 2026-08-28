package machine.code.taskpal.presentation.ui.models

data class VersionHistoryItem(
    val id: String,
    val date: String,
    val time: String,
    val action: VersionAction,
    val taskTitle: String,
    val changeDescription: String? = null,
    val oldDueDate: String? = null,
    val newDueDate: String? = null,
    val priority: String? = null,
    val totalSubtasks: Int? = null
)