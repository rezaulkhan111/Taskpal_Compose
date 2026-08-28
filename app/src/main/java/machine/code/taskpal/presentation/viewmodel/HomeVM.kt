package machine.code.taskpal.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.outlined.ElectricBolt
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.lifecycle.HiltViewModel
import machine.code.taskpal.presentation.ui.models.HeaderInfo
import machine.code.taskpal.presentation.ui.models.QuickActionItem
import machine.code.taskpal.presentation.ui.models.TaskModel
import machine.code.taskpal.presentation.ui.models.TaskType
import machine.code.taskpal.presentation.ui.theme.MainPurple
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor() : BaseVM() {
    private val _headerInfo = mutableStateOf(
        HeaderInfo(
            userName = "Christopher",
            welcomeMessage = "Let's wrap up strong today",
            profileImageUrl = strImageUrl
        )
    )
    val headerInfo: State<HeaderInfo> = _headerInfo

    private val _quickActions = mutableStateOf(
        listOf(
            QuickActionItem(
                title = "Today's Task",
                subtitle = "3 pending • 1 done",
                icon = Icons.Outlined.Schedule,
                iconColor = MainPurple
            ), QuickActionItem(
                title = "Someday List",
                subtitle = "12 tasks",
                icon = Icons.Default.CheckBox,
                iconColor = Color(0xFF4CAF50)
            ), QuickActionItem(
                title = "Your Streak",
                subtitle = "7 days!",
                icon = Icons.Outlined.LocalFireDepartment,
                iconColor = Color(0xFFFF9800)
            ), QuickActionItem(
                title = "History",
                subtitle = "View all",
                icon = Icons.Outlined.ElectricBolt,
                iconColor = Color(0xFF2196F3)
            )
        )
    )
    val quickActions: State<List<QuickActionItem>> = _quickActions

    private val _todayTasks = mutableStateOf(
        listOf(
            TaskModel("Make Sandwich and Pie", "Due 11am today", false, TaskType.TODAY.name),
            TaskModel("Say a prayer by 1pm", "Due 1pm today", false, TaskType.TODAY.name),
            TaskModel("Call my Brother", "Due 3pm today", false, TaskType.TODAY.name)
        )
    )
    val todayTasks: State<List<TaskModel>> = _todayTasks
}
