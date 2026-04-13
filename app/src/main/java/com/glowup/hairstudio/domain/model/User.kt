package com.glowup.hairstudio.domain.model

data class User(
    val id: Int,
    val email: String,
    val password: String,
    val fullName: String,
    val phone: String,
    val role: UserRole,
    val loyaltyVisits: Int = 0,
    val profileImageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
