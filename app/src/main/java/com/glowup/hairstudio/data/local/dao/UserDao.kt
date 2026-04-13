package com.glowup.hairstudio.data.local.dao

import androidx.room.*
import com.glowup.hairstudio.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity): Long
    
    @Update
    suspend fun update(user: UserEntity)
    
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserEntity?>
    
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?
    
    @Query("UPDATE users SET loyaltyVisits = :visits WHERE id = :userId")
    suspend fun updateLoyaltyVisits(userId: Int, visits: Int)
    
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>
    
    @Delete
    suspend fun delete(user: UserEntity)
}
