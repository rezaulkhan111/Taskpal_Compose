package machine.code.taskpal.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import machine.code.taskpal.presentation.ui.components.TaskItem
import machine.code.taskpal.presentation.ui.components.TaskpalBottomNavigation
import machine.code.taskpal.presentation.ui.models.HeaderInfo
import machine.code.taskpal.presentation.ui.models.QuickActionItem
import machine.code.taskpal.presentation.ui.theme.TaskpalTheme
import machine.code.taskpal.presentation.viewmodel.HomeVM

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit, onBack: () -> Unit = {}, viewModel: HomeVM = hiltViewModel()
) {
    val headerInfo = viewModel.headerInfo.value
    val quickActions = viewModel.quickActions.value
    val todayTasks = viewModel.todayTasks.value
    Scaffold(
        bottomBar = {
        TaskpalBottomNavigation(
            currentScreen = "home", onScreenSelected = onNavigate
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Task")
        }
    }, containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            HomeHeader(headerInfo)

            Spacer(modifier = Modifier.height(24.dp))

            // Quick Action Grid
            QuickActionGrid(quickActions)

            Spacer(modifier = Modifier.height(24.dp))

            MotivationCard()

            Spacer(modifier = Modifier.height(32.dp))

            SectionHeader(title = "Today's Task", actionText = "See all")
            Spacer(modifier = Modifier.height(16.dp))

            todayTasks.forEach { task ->
                TaskItem(
                    title = task.title, dueTime = task.dueTime, isCompleted = task.isCompleted
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(68.dp))
        }
    }
}

@Composable
fun HomeHeader(headerInfo: HeaderInfo) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Image
        AsyncImage(
            model = headerInfo.profileImageUrl,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Good morning ${headerInfo.userName} \uD83D\uDC4B",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = headerInfo.welcomeMessage,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun QuickActionGrid(quickActions: List<QuickActionItem>) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        for (i in quickActions.indices step 2) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                QuickActionCard(
                    modifier = Modifier.weight(1f),
                    title = quickActions[i].title,
                    subtitle = quickActions[i].subtitle,
                    icon = quickActions[i].icon,
                    iconColor = quickActions[i].iconColor
                )
                if (i + 1 < quickActions.size) {
                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        title = quickActions[i + 1].title,
                        subtitle = quickActions[i + 1].subtitle,
                        icon = quickActions[i + 1].icon,
                        iconColor = quickActions[i + 1].iconColor
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun QuickActionCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconColor: Color
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(
            width = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconColor.copy(alpha = if (isSystemInDarkTheme()) 0.2f else 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = subtitle,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MotivationCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "\uD83C\uDF89", fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "You've already completed 2 tasks today, one more and you'll beat your streak!",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun SectionHeader(title: String, actionText: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        TextButton(onClick = { }, contentPadding = PaddingValues(0.dp)) {
            Text(
                text = actionText,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TaskpalTheme {
        HomeScreen(onNavigate = {})
    }
}
