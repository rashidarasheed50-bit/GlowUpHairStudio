package com.glowup.hairstudio.domain.usecase.service

import com.glowup.hairstudio.domain.model.Service
import com.glowup.hairstudio.domain.repository.ServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllServicesUseCase @Inject constructor(
    private val serviceRepository: ServiceRepository
) {
    suspend operator fun invoke(): Flow<List<Service>> {
        return serviceRepository.getAllServices()
    }
}
