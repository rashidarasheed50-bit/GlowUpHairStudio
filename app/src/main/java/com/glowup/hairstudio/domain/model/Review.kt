package com.glowup.hairstudio.domain.model

data class Review(
    val id: Int,
    val userId: Int,
    val appointmentId: Int,
    val stylistId: Int,
    val rating: Int, // 1-5
    val comment: String,
    val createdAt: Long = System.currentTimeMillis(),
    // Joined data
    val userName: String? = null,
    val stylistName: String? = null,
    val serviceName: String? = null
)
