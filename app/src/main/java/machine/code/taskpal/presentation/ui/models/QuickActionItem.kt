package machine.code.taskpal.presentation.ui.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class QuickActionItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val iconColor: Color
)
