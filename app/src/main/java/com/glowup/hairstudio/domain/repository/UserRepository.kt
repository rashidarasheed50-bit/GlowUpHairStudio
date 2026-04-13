package com.glowup.hairstudio.domain.repository

import com.glowup.hairstudio.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun signUp(user: User): Result<User>
    suspend fun getUserById(userId: Int): Flow<User?>
    suspend fun updateUser(user: User): Result<Unit>
    suspend fun logout()
    suspend fun getCurrentUser(): User?
    suspend fun updateLoyaltyVisits(userId: Int, visits: Int): Result<Unit>
}
