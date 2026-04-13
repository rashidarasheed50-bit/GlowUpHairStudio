package com.glowup.hairstudio.data.repository

import com.glowup.hairstudio.data.local.dao.UserDao
import com.glowup.hairstudio.data.local.entity.toDomain
import com.glowup.hairstudio.data.local.entity.toEntity
import com.glowup.hairstudio.domain.model.User
import com.glowup.hairstudio.domain.repository.UserRepository
import com.glowup.hairstudio.utils.PreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val preferenceManager: PreferenceManager
) : UserRepository {
    
    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val userEntity = userDao.login(email, password)
            if (userEntity != null) {
                // Save current user ID
                preferenceManager.saveCurrentUserId(userEntity.id)
                Result.success(userEntity.toDomain())
            } else {
                Result.failure(Exception("Invalid email or password"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun signUp(user: User): Result<User> {
        return try {
            // Check if email already exists
            val existingUser = userDao.getUserByEmail(user.email)
            if (existingUser != null) {
                return Result.failure(Exception("Email already registered"))
            }
            
            val userId = userDao.insert(user.toEntity())
            val newUser = user.copy(id = userId.toInt())
            
            // Save current user ID
            preferenceManager.saveCurrentUserId(userId.toInt())
            
            Result.success(newUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getUserById(userId: Int): Flow<User?> {
        return userDao.getUserById(userId).map { it?.toDomain() }
    }
    
    override suspend fun updateUser(user: User): Result<Unit> {
        return try {
            userDao.update(user.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun logout() {
        preferenceManager.clearCurrentUserId()
    }
    
    override suspend fun getCurrentUser(): User? {
        val userId = preferenceManager.getCurrentUserId()
        return if (userId != -1) {
            userDao.getUserById(userId).map { it?.toDomain() }.let { flow ->
                var user: User? = null
                flow.collect { user = it }
                user
            }
        } else {
            null
        }
    }
    
    override suspend fun updateLoyaltyVisits(userId: Int, visits: Int): Result<Unit> {
        return try {
            userDao.updateLoyaltyVisits(userId, visits)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
