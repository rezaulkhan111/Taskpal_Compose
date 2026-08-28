package machine.code.taskpal.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import dagger.hilt.android.AndroidEntryPoint
import machine.code.taskpal.presentation.navigation.AuthNavigation
import machine.code.taskpal.presentation.ui.theme.TaskpalTheme

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskpalTheme {
                val navigationStack = remember { mutableStateListOf("auth_selection") }
                val currentScreen = navigationStack.lastOrNull() ?: "auth_selection"

                val navigate = { screen: String ->
                    if (navigationStack.isEmpty() || navigationStack.last() != screen) {
                        navigationStack.add(screen)
                    }
                }

                val goBack: () -> Unit = {
                    if (navigationStack.size > 1) {
                        navigationStack.removeAt(navigationStack.size - 1)
                    } else {
                        finish()
                    }
                }

                BackHandler {
                    goBack()
                }

                AuthNavigation(
                    currentScreen = currentScreen,
                    onNavigate = navigate,
                    onBack = goBack,
                    onAuthSuccess = {
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}
