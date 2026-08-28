package machine.code.taskpal.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import machine.code.taskpal.presentation.ui.models.TaskModel
import machine.code.taskpal.presentation.ui.models.TaskType
import javax.inject.Inject

@HiltViewModel
class TaskVM @Inject constructor() : BaseVM() {

    var searchQuery by mutableStateOf("")
        private set

    var selectedTab by mutableIntStateOf(0)
        private set

    private val _todayTasks = mutableStateOf(
        listOf(
            TaskModel("Make Sandwich and Pie", "Due 11am today", false, TaskType.TODAY.name),
            TaskModel("Say a prayer by 1pm", "Due 1pm today", false, TaskType.TODAY.name),
            TaskModel("Call my Brother", "Due 3pm today", false, TaskType.TODAY.name),
            TaskModel("Finish Project report", "Due 4pm", false, TaskType.TODAY.name),
            TaskModel(
                "Finish chapter 2 of Purple Hibiscus", "Due 6pm today", false, TaskType.TODAY.name
            ),
            TaskModel("Make Amala and Ewedu", "Due 7pm today", false, TaskType.TODAY.name),
            TaskModel("Take my medicine", "Due 11pm today", false, TaskType.TODAY.name)
        )
    )

    private val _somedayTasks = mutableStateOf(
        listOf(
            TaskModel("Plan summer vacation", "Someday", false, TaskType.SOMEDAY.name),
            TaskModel("Read new novel", "Someday", false, TaskType.SOMEDAY.name)
        )
    )

    val filteredTasks: State<List<TaskModel>> = derivedStateOf {
        val tasks = if (selectedTab == 0) _todayTasks.value else _somedayTasks.value
        if (searchQuery.isBlank()) {
            tasks
        } else {
            tasks.filter { it.title.contains(searchQuery, ignoreCase = true) }
        }
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
    }

    fun onTabSelected(index: Int) {
        selectedTab = index
    }
}
