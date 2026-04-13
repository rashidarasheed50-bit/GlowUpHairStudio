package com.glowup.hairstudio.domain.usecase.review

import com.glowup.hairstudio.domain.model.Review
import com.glowup.hairstudio.domain.repository.ReviewRepository
import javax.inject.Inject

class SubmitReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(
        userId: Int,
        appointmentId: Int,
        stylistId: Int,
        rating: Int,
        comment: String
    ): Result<Unit> {
        // Validation
        if (rating < 1 || rating > 5) {
            return Result.failure(Exception("Rating must be between 1 and 5"))
        }
        
        if (comment.isBlank()) {
            return Result.failure(Exception("Review comment cannot be empty"))
        }
        
        val review = Review(
            id = 0, // Will be auto-generated
            userId = userId,
            appointmentId = appointmentId,
            stylistId = stylistId,
            rating = rating,
            comment = comment
        )
        
        return reviewRepository.submitReview(review)
    }
}
