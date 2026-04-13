package com.glowup.hairstudio.domain.repository

import com.glowup.hairstudio.domain.model.Service
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {
    suspend fun getAllServices(): Flow<List<Service>>
    suspend fun getServiceById(serviceId: Int): Flow<Service?>
    suspend fun addService(service: Service): Result<Unit>
    suspend fun updateService(service: Service): Result<Unit>
    suspend fun deleteService(serviceId: Int): Result<Unit>
}
