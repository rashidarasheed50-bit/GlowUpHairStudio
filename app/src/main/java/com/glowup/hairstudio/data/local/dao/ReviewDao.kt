package com.glowup.hairstudio.data.local.dao

import androidx.room.*
import com.glowup.hairstudio.data.local.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review: ReviewEntity): Long
    
    @Update
    suspend fun update(review: ReviewEntity)
    
    @Delete
    suspend fun delete(review: ReviewEntity)
    
    @Query("SELECT * FROM reviews WHERE userId = :userId ORDER BY createdAt DESC")
    fun getReviewsByUserId(userId: Int): Flow<List<ReviewEntity>>
    
    @Query("SELECT * FROM reviews WHERE stylistId = :stylistId ORDER BY createdAt DESC")
    fun getReviewsByStylistId(stylistId: Int): Flow<List<ReviewEntity>>
    
    @Query("SELECT * FROM reviews ORDER BY createdAt DESC")
    fun getAllReviews(): Flow<List<ReviewEntity>>
    
    @Query("SELECT * FROM reviews WHERE appointmentId = :appointmentId LIMIT 1")
    fun getReviewByAppointmentId(appointmentId: Int): Flow<ReviewEntity?>
    
    @Query("SELECT AVG(rating) FROM reviews WHERE stylistId = :stylistId")
    suspend fun getAverageRatingForStylist(stylistId: Int): Double?
}
