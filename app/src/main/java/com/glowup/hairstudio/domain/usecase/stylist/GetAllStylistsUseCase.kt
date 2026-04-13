package com.glowup.hairstudio.domain.usecase.stylist

import com.glowup.hairstudio.domain.model.Stylist
import com.glowup.hairstudio.domain.repository.StylistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllStylistsUseCase @Inject constructor(
    private val stylistRepository: StylistRepository
) {
    suspend operator fun invoke(): Flow<List<Stylist>> {
        return stylistRepository.getAllStylists()
    }
}
