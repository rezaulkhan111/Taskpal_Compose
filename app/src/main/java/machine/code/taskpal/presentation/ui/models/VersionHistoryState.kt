package machine.code.taskpal.presentation.ui.models

data class VersionHistoryState(
    val historyGroupedByDate: Map<String, List<VersionHistoryItem>> = emptyMap()
)