package machine.code.taskpal.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import machine.code.taskpal.presentation.ui.components.TaskItem
import machine.code.taskpal.presentation.ui.components.TaskpalBottomNavigation
import machine.code.taskpal.presentation.ui.theme.TaskpalTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(onNavigate: (String) -> Unit, onBack: () -> Unit = {}) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Today's Task", "Someday's Task")

    Scaffold(
        bottomBar = { TaskpalBottomNavigation(currentScreen = "tasks", onScreenSelected = onNavigate) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Tasks",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search task...", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant) },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
                divider = {},
                indicator = { tabPositions ->
                    if (selectedTab < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                fontSize = 14.sp,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTab == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            val tasks = if (selectedTab == 0) todayTasks else somedayTasks
            val filteredTasks = tasks.filter { it.title.contains(searchQuery, ignoreCase = true) }
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(filteredTasks) { task ->
                    TaskItem(
                        title = task.title,
                        dueTime = task.dueTime,
                        isCompleted = task.isCompleted
                    )
                }
            }
        }
    }
}

data class TaskData(val title: String, val dueTime: String, val isCompleted: Boolean)

val todayTasks = listOf(
    TaskData("Make Sandwich and Pie", "Due 11am today", false),
    TaskData("Say a prayer by 1pm", "Due 1pm today", false),
    TaskData("Call my Brother", "Due 3pm today", false),
    TaskData("Finish Project report", "Due 4pm", false),
    TaskData("Finish chapter 2 of Purple Hibiscus", "Due 6pm today", false),
    TaskData("Make Amala and Ewedu", "Due 7pm today", false),
    TaskData("Take my medicine", "Due 11pm today", false),
)

val somedayTasks = listOf(
    TaskData("Plan summer vacation", "Someday", false),
    TaskData("Read new novel", "Someday", false),
)

@Preview(showBackground = true)
@Composable
fun TasksScreenPreview() {
    TaskpalTheme {
        TasksScreen(onNavigate = {})
    }
}
