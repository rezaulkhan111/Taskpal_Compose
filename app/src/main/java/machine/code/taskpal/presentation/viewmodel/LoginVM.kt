package machine.code.taskpal.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import machine.code.taskpal.BuildConfig
import machine.code.taskpal.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(private val loginUseCase: LoginUseCase) : BaseVM() {
    var email by mutableStateOf(
        if (BuildConfig.DEBUG) {
            "user@taskpal.com"
        } else {
            ""
        }
    )
    var password by mutableStateOf(
        if (BuildConfig.DEBUG) {
            "password123"
        } else {
            ""
        }
    )
    var isLoading by mutableStateOf(false)
    var loginError by mutableStateOf<String?>(null)

    fun onEmailChange(newValue: String) {
        email = newValue
        loginError = null
    }

    fun onPasswordChange(newValue: String) {
        password = newValue
        loginError = null
    }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            loginError = null

            val result = loginUseCase(email, password)

            result.onSuccess {
                onSuccess()
            }.onFailure {
                loginError = it.message ?: "Invalid email or password"
            }

            isLoading = false
        }
    }
}