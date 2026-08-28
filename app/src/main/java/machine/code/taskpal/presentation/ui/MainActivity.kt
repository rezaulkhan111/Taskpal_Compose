package machine.code.taskpal.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import machine.code.taskpal.presentation.ui.screens.SplashScreen
import machine.code.taskpal.presentation.ui.theme.TaskpalTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            TaskpalTheme {
                LaunchedEffect(Unit) {
                    delay(2000)
                    startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                    finish()
                }

                SplashScreen()
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