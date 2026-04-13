package com.glowup.hairstudio.data.repository

import com.glowup.hairstudio.data.local.dao.ServiceDao
import com.glowup.hairstudio.data.local.entity.toDomain
import com.glowup.hairstudio.data.local.entity.toEntity
import com.glowup.hairstudio.domain.model.Service
import com.glowup.hairstudio.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(
    private val serviceDao: ServiceDao
) : ServiceRepository {
    
    override suspend fun getAllServices(): Flow<List<Service>> {
        return serviceDao.getAllServices().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun getServiceById(serviceId: Int): Flow<Service?> {
        return serviceDao.getServiceById(serviceId).map { it?.toDomain() }
    }
    
    override suspend fun addService(service: Service): Result<Unit> {
        return try {
            serviceDao.insert(service.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun updateService(service: Service): Result<Unit> {
        return try {
            serviceDao.update(service.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun deleteService(serviceId: Int): Result<Unit> {
        return try {
            serviceDao.deleteById(serviceId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
