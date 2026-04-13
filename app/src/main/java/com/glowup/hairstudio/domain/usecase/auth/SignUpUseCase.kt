package com.glowup.hairstudio.domain.usecase.auth

import com.glowup.hairstudio.domain.model.User
import com.glowup.hairstudio.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        fullName: String,
        phone: String
    ): Result<User> {
        // Validation
        if (email.isBlank()) {
            return Result.failure(Exception("Email cannot be empty"))
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(Exception("Invalid email format"))
        }
        if (password.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }
        if (fullName.isBlank()) {
            return Result.failure(Exception("Full name cannot be empty"))
        }
        if (phone.isBlank()) {
            return Result.failure(Exception("Phone number cannot be empty"))
        }
        
        // Create user object
        val user = User(
            id = 0, // Will be auto-generated
            email = email,
            password = password,
            fullName = fullName,
            phone = phone,
            role = com.glowup.hairstudio.domain.model.UserRole.CUSTOMER
        )
        
        return userRepository.signUp(user)
    }
}
