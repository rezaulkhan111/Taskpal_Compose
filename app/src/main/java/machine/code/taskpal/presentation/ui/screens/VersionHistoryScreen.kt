package machine.code.taskpal.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import machine.code.taskpal.presentation.ui.models.VersionAction
import machine.code.taskpal.presentation.ui.models.VersionHistoryItem
import machine.code.taskpal.presentation.ui.theme.TaskpalTheme
import machine.code.taskpal.presentation.viewmodel.VersionHistoryVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VersionHistoryScreen(
    onBack: () -> Unit, viewModel: VersionHistoryVM = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                Text(
                    "Version History",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }, navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }, actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.CalendarMonth, contentDescription = "Filter")
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
            )
        }, containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            state.historyGroupedByDate.forEach { (date, items) ->
                item {
                    Text(
                        text = "🗓️ $date",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                items(items) { item ->
                    VersionHistoryCard(item)
                }
            }
        }
    }
}

@Composable
fun VersionHistoryCard(item: VersionHistoryItem) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 0.dp,
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val (icon, color, label) = when (item.action) {
                    VersionAction.TASK_COMPLETED -> Triple(
                        Icons.Default.Check, Color(0xFF4CAF50), "Task Completed"
                    )

                    VersionAction.TASK_EDITED -> Triple(
                        Icons.Default.Edit, Color(0xFFFFC107), "Task Edited"
                    )

                    VersionAction.SUBTASK_ADDED -> Triple(
                        Icons.Default.Edit, Color(0xFFF44336), "Subtask Added"
                    )
                }

                Surface(
                    color = color, shape = RoundedCornerShape(4.dp), modifier = Modifier.size(16.dp)
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(2.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Task: ${item.taskTitle}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (item.changeDescription != null) {
                Text(
                    text = "Change: ${item.changeDescription}", fontSize = 13.sp, color = Color.Gray
                )
            }

            Text(
                text = "${item.date.split(" - ").last()} - ${item.time}",
                fontSize = 12.sp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (item.action == VersionAction.TASK_EDITED) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Old Due Date:", fontSize = 12.sp, color = Color.Gray)
                        Text(item.oldDueDate ?: "", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("New Due Date:", fontSize = 12.sp, color = Color.Gray)
                        Text(item.newDueDate ?: "", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Priority:", fontSize = 12.sp, color = Color.Gray)
                        Text(item.priority ?: "", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            if (item.action == VersionAction.SUBTASK_ADDED) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 0.5.dp,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total Subtasks:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(
                        item.totalSubtasks?.toString() ?: "0",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            } else {
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    Text(
                        "Restore this Version",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VersionHistoryScreenPreview() {
    TaskpalTheme {
        VersionHistoryScreen(onBack = {})
    }
}
