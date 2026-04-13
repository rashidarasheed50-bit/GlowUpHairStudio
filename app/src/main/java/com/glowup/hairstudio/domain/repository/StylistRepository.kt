package com.glowup.hairstudio.domain.repository

import com.glowup.hairstudio.domain.model.Stylist
import kotlinx.coroutines.flow.Flow

interface StylistRepository {
    suspend fun getAllStylists(): Flow<List<Stylist>>
    suspend fun getStylistById(stylistId: Int): Flow<Stylist?>
    suspend fun addStylist(stylist: Stylist): Result<Unit>
    suspend fun updateStylist(stylist: Stylist): Result<Unit>
}
