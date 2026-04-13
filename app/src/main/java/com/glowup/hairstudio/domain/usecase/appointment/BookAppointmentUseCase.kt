package com.glowup.hairstudio.domain.usecase.appointment

import com.glowup.hairstudio.domain.model.Appointment
import com.glowup.hairstudio.domain.model.AppointmentStatus
import com.glowup.hairstudio.domain.repository.AppointmentRepository
import javax.inject.Inject

class BookAppointmentUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    suspend operator fun invoke(
        userId: Int,
        serviceId: Int,
        stylistId: Int,
        dateTime: Long,
        duration: Int,
        price: Double,
        specialRequests: String? = null
    ): Result<Unit> {
        // Validation
        val currentTime = System.currentTimeMillis()
        if (dateTime < currentTime) {
            return Result.failure(Exception("Cannot book appointment in the past"))
        }
        
        if (price <= 0) {
            return Result.failure(Exception("Invalid price"))
        }
        
        if (duration <= 0) {
            return Result.failure(Exception("Invalid duration"))
        }
        
        val appointment = Appointment(
            id = 0, // Will be auto-generated
            userId = userId,
            serviceId = serviceId,
            stylistId = stylistId,
            dateTime = dateTime,
            duration = duration,
            price = price,
            status = AppointmentStatus.PENDING,
            specialRequests = specialRequests
        )
        
        return appointmentRepository.bookAppointment(appointment)
    }
}
