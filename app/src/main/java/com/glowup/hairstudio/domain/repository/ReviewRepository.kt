package com.glowup.hairstudio.domain.repository

import com.glowup.hairstudio.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    suspend fun submitReview(review: Review): Result<Unit>
    suspend fun getReviewsByUserId(userId: Int): Flow<List<Review>>
    suspend fun getReviewsByStylistId(stylistId: Int): Flow<List<Review>>
    suspend fun getAllReviews(): Flow<List<Review>>
    suspend fun getReviewByAppointmentId(appointmentId: Int): Flow<Review?>
}
