package com.glowup.hairstudio.domain.usecase.auth

import com.glowup.hairstudio.domain.repository.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        userRepository.logout()
    }
}
