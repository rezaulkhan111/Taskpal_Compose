package machine.code.taskpal.ui

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import machine.code.taskpal.ui.theme.TaskpalTheme

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
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

            HomeHeader()

            Spacer(modifier = Modifier.height(24.dp))

            QuickActionGrid()

            Spacer(modifier = Modifier.height(24.dp))

            MotivationCard()

            Spacer(modifier = Modifier.height(32.dp))

            SectionHeader(title = "Today's Task", actionText = "See all")
            Spacer(modifier = Modifier.height(16.dp))

            TaskItem(
                title = "Make Sandwich and Pie", dueTime = "Due 11am today", isCompleted = false
            )

            Spacer(modifier = Modifier.height(12.dp))

            TaskItem(
                title = "Say a prayer by 1pm", dueTime = "Due 1pm today", isCompleted = false
            )

            Spacer(modifier = Modifier.height(12.dp))

            TaskItem(
                title = "Call my Brother", dueTime = "Due 3pm today", isCompleted = false
            )

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Good morning Christopher \uD83D\uDC4B",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Let's wrap up strong today",
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
fun QuickActionGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Today's Task",
                subtitle = "3 pending • 1 done",
                icon = Icons.Outlined.Schedule,
                iconColor = MaterialTheme.colorScheme.primary
            )
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Someday List",
                subtitle = "12 tasks",
                icon = Icons.Default.CheckBox,
                iconColor = Color(0xFF4CAF50)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "Your Streak",
                subtitle = "7 days!",
                icon = Icons.Outlined.LocalFireDepartment,
                iconColor = Color(0xFFFF9800)
            )
            QuickActionCard(
                modifier = Modifier.weight(1f),
                title = "History",
                subtitle = "View all",
                icon = Icons.Outlined.ElectricBolt,
                iconColor = Color(0xFF2196F3)
            )
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
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 0.5.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconColor.copy(alpha = 0.1f)), contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
