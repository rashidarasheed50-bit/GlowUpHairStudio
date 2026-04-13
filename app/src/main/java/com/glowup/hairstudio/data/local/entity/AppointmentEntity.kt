package com.glowup.hairstudio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glowup.hairstudio.domain.model.Appointment
import com.glowup.hairstudio.domain.model.AppointmentStatus

@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val serviceId: Int,
    val stylistId: Int,
    val dateTime: Long,
    val duration: Int, // in minutes
    val price: Double,
    val status: String, // "PENDING", "CONFIRMED", "COMPLETED", "CANCELLED"
    val specialRequests: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

// Mapper functions
fun AppointmentEntity.toDomain(
    serviceName: String? = null,
    stylistName: String? = null,
    userName: String? = null
): Appointment {
    return Appointment(
        id = id,
        userId = userId,
        serviceId = serviceId,
        stylistId = stylistId,
        dateTime = dateTime,
        duration = duration,
        price = price,
        status = AppointmentStatus.valueOf(status),
        specialRequests = specialRequests,
        createdAt = createdAt,
        serviceName = serviceName,
        stylistName = stylistName,
        userName = userName
    )
}

fun Appointment.toEntity(): AppointmentEntity {
    return AppointmentEntity(
        id = id,
        userId = userId,
        serviceId = serviceId,
        stylistId = stylistId,
        dateTime = dateTime,
        duration = duration,
        price = price,
        status = status.name,
        specialRequests = specialRequests,
        createdAt = createdAt
    )
}
