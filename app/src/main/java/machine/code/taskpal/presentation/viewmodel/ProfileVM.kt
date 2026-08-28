package machine.code.taskpal.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Timer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import machine.code.taskpal.presentation.ui.models.PreferenceItem
import machine.code.taskpal.presentation.ui.models.ProfileState
import machine.code.taskpal.presentation.ui.models.SupportItemData
import javax.inject.Inject

@HiltViewModel
class ProfileVM @Inject constructor() : BaseVM() {
    private val _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        _uiState.value = ProfileState(
            userName = "Christopher Johnson", profileImage = strImageUrl, preferences = listOf(
                PreferenceItem(
                    "motivation", Icons.Outlined.NotificationsActive, "Motivation Nudges", true
                ), PreferenceItem(
                    "streak", Icons.Outlined.LocalFireDepartment, "Streak Reminders", true
                ), PreferenceItem("deadline", Icons.Outlined.Timer, "Task Deadline Reminders", true)
            ), supportInfo = listOf(
                SupportItemData("help", Icons.AutoMirrored.Outlined.HelpOutline, "Help & Support"),
                SupportItemData("privacy", Icons.Outlined.Shield, "Privacy Policy"),
                SupportItemData("terms", Icons.Outlined.Description, "Terms of Service")
            )
        )
    }

    fun togglePreference(id: String, isEnabled: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                preferences = currentState.preferences.map {
                    if (it.id == id) it.copy(isEnabled = isEnabled) else it
                })
        }
    }

    fun logout() {
        // Handle logout logic here
    }
}
