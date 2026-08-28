package machine.code.taskpal.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogoIcon(modifier: Modifier = Modifier, color: Color = Color.White) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .width(6.dp)
                .height(16.dp)
                .background(color, RoundedCornerShape(3.dp))
        )
        Box(
            modifier = Modifier
                .width(6.dp)
                .height(28.dp)
                .background(color, RoundedCornerShape(3.dp))
        )
        Box(
            modifier = Modifier
                .width(6.dp)
                .height(16.dp)
                .background(color, RoundedCornerShape(3.dp))
        )
    }
}

@Composable
fun TaskpalBottomNavigation(currentScreen: String, onScreenSelected: (String) -> Unit) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        val selectedColor = MaterialTheme.colorScheme.primary
        val unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
        
        NavigationBarItem(
            selected = currentScreen == "home",
            onClick = { onScreenSelected("home") },
            icon = { Icon(if (currentScreen == "home") Icons.Filled.Home else Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                unselectedIconColor = unselectedColor,
                unselectedTextColor = unselectedColor,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentScreen == "tasks",
            onClick = { onScreenSelected("tasks") },
            icon = { Icon(Icons.Outlined.TaskAlt, contentDescription = "Tasks") },
            label = { Text("Tasks") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                unselectedIconColor = unselectedColor,
                unselectedTextColor = unselectedColor,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentScreen == "streaks",
            onClick = { onScreenSelected("streaks") },
            icon = { Icon(Icons.Outlined.Insights, contentDescription = "Streaks") },
            label = { Text("Streaks") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                unselectedIconColor = unselectedColor,
                unselectedTextColor = unselectedColor,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentScreen == "account",
            onClick = { onScreenSelected("account") },
            icon = { Icon(Icons.Outlined.AccountCircle, contentDescription = "Account") },
            label = { Text("Account") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                unselectedIconColor = unselectedColor,
                unselectedTextColor = unselectedColor,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun TaskItem(title: String, dueTime: String, isCompleted: Boolean) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 0.5.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isCompleted, 
                onClick = { },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.AccessTime,
                        contentDescription = null,
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = dueTime, fontSize = 13.sp, color = Color(0xFFFF9800))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.MoreVert, 
                contentDescription = null, 
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
