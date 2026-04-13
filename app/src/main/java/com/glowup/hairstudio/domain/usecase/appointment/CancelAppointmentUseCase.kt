package com.glowup.hairstudio.domain.usecase.appointment

import com.glowup.hairstudio.domain.repository.AppointmentRepository
import javax.inject.Inject

class CancelAppointmentUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    suspend operator fun invoke(appointmentId: Int): Result<Unit> {
        return appointmentRepository.cancelAppointment(appointmentId)
    }
}
