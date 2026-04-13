package com.glowup.hairstudio.domain.usecase.loyalty

import com.glowup.hairstudio.domain.model.LoyaltyProgress
import com.glowup.hairstudio.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLoyaltyProgressUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Int): Flow<LoyaltyProgress> {
        return userRepository.getUserById(userId).map { user ->
            val visits = user?.loyaltyVisits ?: 0
            LoyaltyProgress(currentVisits = visits)
        }
    }
}
