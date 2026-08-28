package machine.code.taskpal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import machine.code.taskpal.presentation.ui.screens.*
import machine.code.taskpal.presentation.viewmodel.LoginVM

@Composable
fun AuthNavigation(
    currentScreen: String,
    onNavigate: (String) -> Unit,
    onBack: () -> Unit,
    onAuthSuccess: () -> Unit
) {
    when (currentScreen) {
        "auth_selection" -> AuthSelectionScreen(
            onRegisterClick = { onNavigate("registration") },
            onSignInClick = { onNavigate("sign_in") },
            onBack = onBack
        )
        "registration" -> RegistrationScreen(
            onBackClick = onBack,
            onRegisterSuccess = onAuthSuccess
        )
        "sign_in" -> {
            SignInScreen(
                onBackClick = onBack,
                onRegisterClick = { onNavigate("registration") },
                onSignInSuccess = onAuthSuccess
            )
        }
    }
}

@Composable
fun HomeNavigation(
    currentScreen: String,
    onNavigate: (String) -> Unit,
    onBack: () -> Unit
) {
    when (currentScreen) {
        "home" -> HomeScreen(onNavigate = onNavigate, onBack = onBack)
        "tasks" -> TasksScreen(onNavigate = onNavigate, onBack = onBack)
        "streaks" -> StreaksScreen(onNavigate = onNavigate, onBack = onBack)
        "account" -> AccountScreen(onNavigate = onNavigate, onBack = onBack)
    }
}

// Keeping this for backward compatibility if needed, but we should use the split ones
@Composable
fun TaskpalNavigation(
    currentScreen: String, onNavigate: (String) -> Unit, onBack: () -> Unit
) {
    when (currentScreen) {
        "splash" -> SplashScreen()
        "auth_selection", "registration", "sign_in" -> {
            AuthNavigation(currentScreen, onNavigate, onBack, onAuthSuccess = { onNavigate("home") })
        }
        "home", "tasks", "streaks", "account" -> {
            HomeNavigation(currentScreen, onNavigate, onBack)
        }
    }
}
