package machine.code.taskpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import machine.code.taskpal.ui.AuthSelectionScreen
import machine.code.taskpal.ui.AccountScreen
import machine.code.taskpal.ui.HomeScreen
import machine.code.taskpal.ui.RegistrationScreen
import machine.code.taskpal.ui.SignInScreen
import machine.code.taskpal.ui.SplashScreen
import machine.code.taskpal.ui.StreaksScreen
import machine.code.taskpal.ui.TasksScreen
import machine.code.taskpal.ui.theme.TaskpalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskpalTheme {
                var currentScreen by remember { mutableStateOf("splash") }
                
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(2000)
                    if (currentScreen == "splash") {
                        currentScreen = "auth_selection"
                    }
                }

                when (currentScreen) {
                    "splash" -> SplashScreen()
                    "auth_selection" -> AuthSelectionScreen(
                        onRegisterClick = { currentScreen = "registration" },
                        onSignInClick = { currentScreen = "sign_in" }
                    )
                    "registration" -> RegistrationScreen(
                        onBackClick = { currentScreen = "sign_in" },
                        onRegisterSuccess = { currentScreen = "home" }
                    )
                    "sign_in" -> SignInScreen(
                        onBackClick = { currentScreen = "auth_selection" },
                        onRegisterClick = { currentScreen = "registration" },
                        onSignInSuccess = { currentScreen = "home" }
                    )
                    "home" -> HomeScreen(onNavigate = { currentScreen = it })
                    "tasks" -> TasksScreen(onNavigate = { currentScreen = it })
                    "streaks" -> StreaksScreen(onNavigate = { currentScreen = it })
                    "account" -> AccountScreen(onNavigate = { currentScreen = it })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenMainPreview() {
    TaskpalTheme {
        SplashScreen()
    }
}