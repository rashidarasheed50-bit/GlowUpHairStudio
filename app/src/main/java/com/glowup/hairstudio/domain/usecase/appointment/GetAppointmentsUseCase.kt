package com.glowup.hairstudio.domain.usecase.appointment

import com.glowup.hairstudio.domain.model.Appointment
import com.glowup.hairstudio.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppointmentsUseCase @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    suspend operator fun invoke(userId: Int): Flow<List<Appointment>> {
        return appointmentRepository.getAppointmentsByUserId(userId)
    }
}
