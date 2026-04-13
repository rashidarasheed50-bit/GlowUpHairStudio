package com.glowup.hairstudio.domain.usecase.review

import com.glowup.hairstudio.domain.model.Review
import com.glowup.hairstudio.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReviewsUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    suspend fun getByUserId(userId: Int): Flow<List<Review>> {
        return reviewRepository.getReviewsByUserId(userId)
    }
    
    suspend fun getByStylistId(stylistId: Int): Flow<List<Review>> {
        return reviewRepository.getReviewsByStylistId(stylistId)
    }
    
    suspend fun getAll(): Flow<List<Review>> {
        return reviewRepository.getAllReviews()
    }
}
