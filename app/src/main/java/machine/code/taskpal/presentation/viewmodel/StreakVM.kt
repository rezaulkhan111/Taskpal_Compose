package machine.code.taskpal.presentation.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import machine.code.taskpal.presentation.ui.models.DayProgress
import machine.code.taskpal.presentation.ui.models.StreakState
import javax.inject.Inject

@HiltViewModel
class StreakVM @Inject constructor() : BaseVM() {
    private val _streakState = MutableStateFlow(StreakState())
    val streakState: StateFlow<StreakState> = _streakState.asStateFlow()

    init {
        loadStreakData()
    }

    private fun loadStreakData() {
        val days = listOf("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun")
        val completed = listOf(true, true, true, false, true, true, false)

        val progress = days.mapIndexed { index, day ->
            DayProgress(day, completed[index])
        }

        _streakState.value = StreakState(
            streakCount = 6, weeklyProgress = progress, totalDays = 7
        )
    }
}
