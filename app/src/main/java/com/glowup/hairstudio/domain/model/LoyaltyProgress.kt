package com.glowup.hairstudio.domain.model

data class LoyaltyProgress(
    val currentVisits: Int,
    val totalRequired: Int = 10,
    val progressPercentage: Float = (currentVisits.toFloat() / totalRequired) * 100,
    val visitsRemaining: Int = totalRequired - currentVisits,
    val isFreeVisitEarned: Boolean = currentVisits >= totalRequired
)
