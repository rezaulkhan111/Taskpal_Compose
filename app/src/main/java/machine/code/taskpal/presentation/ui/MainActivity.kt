package machine.code.taskpal.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import machine.code.taskpal.data.repository.AuthRepositoryImpl
import machine.code.taskpal.domain.usecase.LoginUseCase
import machine.code.taskpal.presentation.navigation.TaskpalNavigation
import machine.code.taskpal.presentation.ui.screens.SplashScreen
import machine.code.taskpal.presentation.ui.theme.TaskpalTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            TaskpalTheme {
                val navigationStack = remember { mutableStateListOf("splash") }
                val currentScreen = navigationStack.lastOrNull() ?: "splash"
                
                val navigate = { screen: String ->
                    if (navigationStack.isEmpty() || navigationStack.last() != screen) {
                        navigationStack.add(screen)
                    }
                }

                val goBack = {
                    if (navigationStack.size > 1) {
                        navigationStack.removeAt(navigationStack.size - 1)
                    }
                }

                BackHandler(enabled = navigationStack.size > 1) {
                    goBack()
                }

                LaunchedEffect(Unit) {
                    delay(2000)
                    if (currentScreen == "splash") {
                        navigationStack.clear()
                        navigate("auth_selection")
                    }
                }

                TaskpalNavigation(
                    currentScreen = currentScreen,
                    onNavigate = navigate,
                    onBack = goBack
                )
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