package machine.code.taskpal.presentation.ui.models

data class ProfileState(
    val userName: String = "",
    val profileImage: String = "",
    val preferences: List<PreferenceItem> = emptyList(),
    val supportInfo: List<SupportItemData> = emptyList()
)