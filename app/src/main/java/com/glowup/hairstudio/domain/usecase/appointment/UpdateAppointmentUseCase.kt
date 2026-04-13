package com.glowup.hairstudio.domain.usecase.appointment

import com.glowup.hairstudio.domain.model.AppointmentStatus
import com.glowup.hairstudio.domain.repository.AppointmentRepository
import javax.inject.Inject

class UpdateAppointmentUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    suspend operator fun invoke(appointmentId: Int, status: AppointmentStatus): Result<Unit> {
        return appointmentRepository.updateAppointmentStatus(appointmentId, status)
    }
}
