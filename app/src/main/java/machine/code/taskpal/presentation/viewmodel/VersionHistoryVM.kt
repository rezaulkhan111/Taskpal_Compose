package machine.code.taskpal.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import machine.code.taskpal.presentation.ui.models.VersionAction
import machine.code.taskpal.presentation.ui.models.VersionHistoryItem
import machine.code.taskpal.presentation.ui.models.VersionHistoryState
import javax.inject.Inject

@HiltViewModel
class VersionHistoryVM @Inject constructor() : BaseVM() {
    private val _uiState = MutableStateFlow(VersionHistoryState())
    val uiState: StateFlow<VersionHistoryState> = _uiState.asStateFlow()

    init {
        loadVersionHistory()
    }

    private fun loadVersionHistory() {
        val history = listOf(
            VersionHistoryItem(
                id = "1",
                date = "Today - Sep 18, 2025",
                time = "10:42 AM",
                action = VersionAction.TASK_COMPLETED,
                taskTitle = "Finish presentation slides"
            ), VersionHistoryItem(
                id = "2",
                date = "Today - Sep 18, 2025",
                time = "9:15 AM",
                action = VersionAction.TASK_EDITED,
                taskTitle = "Say a Prayer by 5pm",
                changeDescription = "Due date updated → Sep 18, 2:00 PM",
                oldDueDate = "Sep 19, 5:00 PM",
                newDueDate = "Sep 18, 2:00 PM",
                priority = "High"
            ), VersionHistoryItem(
                id = "3",
                date = "Today - Sep 18, 2025",
                time = "8:55 AM",
                action = VersionAction.SUBTASK_ADDED,
                taskTitle = "Finish Project report",
                changeDescription = "Subtask: \"Outline project scope\"",
                totalSubtasks = 3
            )
        )

        _uiState.value = VersionHistoryState(
            historyGroupedByDate = history.groupBy { it.date })
    }
}
