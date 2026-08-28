package machine.code.taskpal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import machine.code.taskpal.presentation.ui.screens.*
import machine.code.taskpal.presentation.viewmodel.LoginVM

@Composable
fun TaskpalNavigation(
    currentScreen: String, onNavigate: (String) -> Unit, onBack: () -> Unit
) {
    when (currentScreen) {
        "splash" -> SplashScreen()
        "auth_selection" -> AuthSelectionScreen(
            onRegisterClick = { onNavigate("registration") },
            onSignInClick = { onNavigate("sign_in") },
            onBack = onBack
        )

        "registration" -> RegistrationScreen(
            onBackClick = onBack, onRegisterSuccess = { onNavigate("home") })

        "sign_in" -> {
            SignInScreen(
                onBackClick = onBack,
                onRegisterClick = { onNavigate("registration") },
                onSignInSuccess = { onNavigate("home") })
        }

        "home" -> HomeScreen(onNavigate = onNavigate, onBack = onBack)
        "tasks" -> TasksScreen(onNavigate = onNavigate, onBack = onBack)
        "streaks" -> StreaksScreen(onNavigate = onNavigate, onBack = onBack)
        "account" -> AccountScreen(onNavigate = onNavigate, onBack = onBack)
    }
}
