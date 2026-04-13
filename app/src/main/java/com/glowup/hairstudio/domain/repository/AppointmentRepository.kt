package com.glowup.hairstudio.domain.repository

import com.glowup.hairstudio.domain.model.Appointment
import com.glowup.hairstudio.domain.model.AppointmentStatus
import kotlinx.coroutines.flow.Flow

interface AppointmentRepository {
    suspend fun bookAppointment(appointment: Appointment): Result<Unit>
    suspend fun getAppointmentsByUserId(userId: Int): Flow<List<Appointment>>
    suspend fun getAllAppointments(): Flow<List<Appointment>>
    suspend fun getAppointmentById(appointmentId: Int): Flow<Appointment?>
    suspend fun updateAppointmentStatus(appointmentId: Int, status: AppointmentStatus): Result<Unit>
    suspend fun cancelAppointment(appointmentId: Int): Result<Unit>
    suspend fun getUpcomingAppointments(userId: Int): Flow<List<Appointment>>
    suspend fun getPastAppointments(userId: Int): Flow<List<Appointment>>
    suspend fun getTodayAppointments(): Flow<List<Appointment>>
}
