package com.glowup.hairstudio.data.repository

import com.glowup.hairstudio.data.local.dao.AppointmentDao
import com.glowup.hairstudio.data.local.dao.ReviewDao
import com.glowup.hairstudio.data.local.dao.ServiceDao
import com.glowup.hairstudio.data.local.dao.StylistDao
import com.glowup.hairstudio.data.local.dao.UserDao
import com.glowup.hairstudio.data.local.entity.toDomain
import com.glowup.hairstudio.data.local.entity.toEntity
import com.glowup.hairstudio.domain.model.Review
import com.glowup.hairstudio.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewDao: ReviewDao,
    private val userDao: UserDao,
    private val stylistDao: StylistDao,
    private val appointmentDao: AppointmentDao,
    private val serviceDao: ServiceDao
) : ReviewRepository {
    
    override suspend fun submitReview(review: Review): Result<Unit> {
        return try {
            reviewDao.insert(review.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getReviewsByUserId(userId: Int): Flow<List<Review>> {
        return reviewDao.getReviewsByUserId(userId).map { entities ->
            entities.map { entity ->
                val stylist = stylistDao.getStylistById(entity.stylistId).first()
                val appointment = appointmentDao.getAppointmentById(entity.appointmentId).first()
                val service = appointment?.let { serviceDao.getServiceById(it.serviceId).first() }
                
                entity.toDomain(
                    stylistName = stylist?.name,
                    serviceName = service?.name
                )
            }
        }
    }
    
    override suspend fun getReviewsByStylistId(stylistId: Int): Flow<List<Review>> {
        return reviewDao.getReviewsByStylistId(stylistId).map { entities ->
            entities.map { entity ->
                val user = userDao.getUserById(entity.userId).first()
                val appointment = appointmentDao.getAppointmentById(entity.appointmentId).first()
                val service = appointment?.let { serviceDao.getServiceById(it.serviceId).first() }
                
                entity.toDomain(
                    userName = user?.fullName,
                    serviceName = service?.name
                )
            }
        }
    }
    
    override suspend fun getAllReviews(): Flow<List<Review>> {
        return reviewDao.getAllReviews().map { entities ->
            entities.map { entity ->
                val user = userDao.getUserById(entity.userId).first()
                val stylist = stylistDao.getStylistById(entity.stylistId).first()
                val appointment = appointmentDao.getAppointmentById(entity.appointmentId).first()
                val service = appointment?.let { serviceDao.getServiceById(it.serviceId).first() }
                
                entity.toDomain(
                    userName = user?.fullName,
                    stylistName = stylist?.name,
                    serviceName = service?.name
                )
            }
        }
    }
    
    override suspend fun getReviewByAppointmentId(appointmentId: Int): Flow<Review?> {
        return reviewDao.getReviewByAppointmentId(appointmentId).map { entity ->
            entity?.let {
                val user = userDao.getUserById(it.userId).first()
                val stylist = stylistDao.getStylistById(it.stylistId).first()
                val appointment = appointmentDao.getAppointmentById(it.appointmentId).first()
                val service = appointment?.let { serviceDao.getServiceById(it.serviceId).first() }
                
                it.toDomain(
                    userName = user?.fullName,
                    stylistName = stylist?.name,
                    serviceName = service?.name
                )
            }
        }
    }
}
