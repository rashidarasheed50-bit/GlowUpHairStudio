package com.glowup.hairstudio.domain.model

data class Appointment(
    val id: Int,
    val userId: Int,
    val serviceId: Int,
    val stylistId: Int,
    val dateTime: Long,
    val duration: Int, // in minutes
    val price: Double,
    val status: AppointmentStatus,
    val specialRequests: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    // Joined data
    val serviceName: String? = null,
    val stylistName: String? = null,
    val userName: String? = null
)
