package com.glowup.hairstudio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glowup.hairstudio.domain.model.Review

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val appointmentId: Int,
    val stylistId: Int,
    val rating: Int, // 1-5
    val comment: String,
    val createdAt: Long = System.currentTimeMillis()
)

// Mapper functions
fun ReviewEntity.toDomain(
    userName: String? = null,
    stylistName: String? = null,
    serviceName: String? = null
): Review {
    return Review(
        id = id,
        userId = userId,
        appointmentId = appointmentId,
        stylistId = stylistId,
        rating = rating,
        comment = comment,
        createdAt = createdAt,
        userName = userName,
        stylistName = stylistName,
        serviceName = serviceName
    )
}

fun Review.toEntity(): ReviewEntity {
    return ReviewEntity(
        id = id,
        userId = userId,
        appointmentId = appointmentId,
        stylistId = stylistId,
        rating = rating,
        comment = comment,
        createdAt = createdAt
    )
}
