package com.glowup.hairstudio.domain.usecase.auth

import com.glowup.hairstudio.domain.model.User
import com.glowup.hairstudio.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        if (email.isBlank()) {
            return Result.failure(Exception("Email cannot be empty"))
        }
        if (password.isBlank()) {
            return Result.failure(Exception("Password cannot be empty"))
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(Exception("Invalid email format"))
        }
        
        return userRepository.login(email, password)
    }
}
