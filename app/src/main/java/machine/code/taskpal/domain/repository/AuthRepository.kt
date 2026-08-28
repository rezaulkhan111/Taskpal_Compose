package machine.code.taskpal.domain.repository

import machine.code.taskpal.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(fullName: String, email: String, password: String): Result<User>
    suspend fun logout()
    suspend fun getCurrentUser(): User?
}
