package machine.code.taskpal.data.repository

import kotlinx.coroutines.delay
import machine.code.taskpal.domain.model.User
import machine.code.taskpal.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        delay(1500) // Simulate network
        return if (email == "user@taskpal.com" && password == "password123") {
            Result.success(User("1", email, "Christopher Johnson"))
        } else {
            Result.failure(Exception("Invalid email or password"))
        }
    }

    override suspend fun register(fullName: String, email: String, password: String): Result<User> {
        delay(1500)
        return Result.success(User("2", email, fullName))
    }

    override suspend fun logout() {
        // Clear local storage
    }

    override suspend fun getCurrentUser(): User? {
        return null
    }
}
