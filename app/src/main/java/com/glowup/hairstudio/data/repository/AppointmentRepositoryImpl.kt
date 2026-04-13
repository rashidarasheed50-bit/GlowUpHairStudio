package com.glowup.hairstudio.data.repository

import com.glowup.hairstudio.data.local.dao.AppointmentDao
import com.glowup.hairstudio.data.local.dao.ServiceDao
import com.glowup.hairstudio.data.local.dao.StylistDao
import com.glowup.hairstudio.data.local.dao.UserDao
import com.glowup.hairstudio.data.local.entity.toDomain
import com.glowup.hairstudio.data.local.entity.toEntity
import com.glowup.hairstudio.domain.model.Appointment
import com.glowup.hairstudio.domain.model.AppointmentStatus
import com.glowup.hairstudio.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val appointmentDao: AppointmentDao,
    private val serviceDao: ServiceDao,
    private val stylistDao: StylistDao,
    private val userDao: UserDao
) : AppointmentRepository {
    
    override suspend fun bookAppointment(appointment: Appointment): Result<Unit> {
        return try {
            appointmentDao.insert(appointment.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getAppointmentsByUserId(userId: Int): Flow<List<Appointment>> {
        return appointmentDao.getAppointmentsByUserId(userId).map { entities ->
            entities.map { entity ->
                val service = serviceDao.getServiceById(entity.serviceId).first()
                val stylist = stylistDao.getStylistById(entity.stylistId).first()
                entity.toDomain(
                    serviceName = service?.name,
                    stylistName = stylist?.name
                )
            }
        }
    }
    
    override suspend fun getAllAppointments(): Flow<List<Appointment>> {
        return appointmentDao.getAllAppointments().map { entities ->
            entities.map { entity ->
                val service = serviceDao.getServiceById(entity.serviceId).first()
                val stylist = stylistDao.getStylistById(entity.stylistId).first()
                val user = userDao.getUserById(entity.userId).first()
                entity.toDomain(
                    serviceName = service?.name,
                    stylistName = stylist?.name,
                    userName = user?.fullName
                )
            }
        }
    }
    
    override suspend fun getAppointmentById(appointmentId: Int): Flow<Appointment?> {
        return appointmentDao.getAppointmentById(appointmentId).map { entity ->
            entity?.let {
                val service = serviceDao.getServiceById(it.serviceId).first()
                val stylist = stylistDao.getStylistById(it.stylistId).first()
                it.toDomain(
                    serviceName = service?.name,
                    stylistName = stylist?.name
                )
            }
        }
    }
    
    override suspend fun updateAppointmentStatus(appointmentId: Int, status: AppointmentStatus): Result<Unit> {
        return try {
            appointmentDao.updateStatus(appointmentId, status.name)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun cancelAppointment(appointmentId: Int): Result<Unit> {
        return try {
            appointmentDao.updateStatus(appointmentId, AppointmentStatus.CANCELLED.name)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getUpcomingAppointments(userId: Int): Flow<List<Appointment>> {
        val currentTime = System.currentTimeMillis()
        return appointmentDao.getUpcomingAppointments(userId, currentTime).map { entities ->
            entities.map { entity ->
                val service = serviceDao.getServiceById(entity.serviceId).first()
                val stylist = stylistDao.getStylistById(entity.stylistId).first()
                entity.toDomain(
                    serviceName = service?.name,
                    stylistName = stylist?.name
                )
            }
        }
    }
    
    override suspend fun getPastAppointments(userId: Int): Flow<List<Appointment>> {
        val currentTime = System.currentTimeMillis()
        return appointmentDao.getPastAppointments(userId, currentTime).map { entities ->
            entities.map { entity ->
                val service = serviceDao.getServiceById(entity.serviceId).first()
                val stylist = stylistDao.getStylistById(entity.stylistId).first()
                entity.toDomain(
                    serviceName = service?.name,
                    stylistName = stylist?.name
                )
            }
        }
    }
    
    override suspend fun getTodayAppointments(): Flow<List<Appointment>> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val startOfDay = calendar.timeInMillis
        
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        val endOfDay = calendar.timeInMillis
        
        return appointmentDao.getTodayAppointments(startOfDay, endOfDay).map { entities ->
            entities.map { entity ->
                val service = serviceDao.getServiceById(entity.serviceId).first()
                val stylist = stylistDao.getStylistById(entity.stylistId).first()
                val user = userDao.getUserById(entity.userId).first()
                entity.toDomain(
                    serviceName = service?.name,
                    stylistName = stylist?.name,
                    userName = user?.fullName
                )
            }
        }
    }
}
