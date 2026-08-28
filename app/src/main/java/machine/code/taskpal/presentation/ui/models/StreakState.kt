package machine.code.taskpal.presentation.ui.models

data class StreakState(
    val streakCount: Int = 0,
    val weeklyProgress: List<DayProgress> = emptyList(),
    val totalDays: Int = 7
) {
    val completedDaysCount: Int get() = weeklyProgress.count { it.isCompleted }
    val progressFraction: Float get() = if (totalDays > 0) completedDaysCount.toFloat() / totalDays else 0f
}