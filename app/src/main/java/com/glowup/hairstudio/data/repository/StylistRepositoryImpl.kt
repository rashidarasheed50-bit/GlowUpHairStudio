package com.glowup.hairstudio.data.repository

import com.glowup.hairstudio.data.local.dao.StylistDao
import com.glowup.hairstudio.data.local.entity.toDomain
import com.glowup.hairstudio.data.local.entity.toEntity
import com.glowup.hairstudio.domain.model.Stylist
import com.glowup.hairstudio.domain.repository.StylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StylistRepositoryImpl @Inject constructor(
    private val stylistDao: StylistDao
) : StylistRepository {
    
    override suspend fun getAllStylists(): Flow<List<Stylist>> {
        return stylistDao.getAllStylists().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun getStylistById(stylistId: Int): Flow<Stylist?> {
        return stylistDao.getStylistById(stylistId).map { it?.toDomain() }
    }
    
    override suspend fun addStylist(stylist: Stylist): Result<Unit> {
        return try {
            stylistDao.insert(stylist.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun updateStylist(stylist: Stylist): Result<Unit> {
        return try {
            stylistDao.update(stylist.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
