package com.glowup.hairstudio.data.local.dao

import androidx.room.*
import com.glowup.hairstudio.data.local.entity.ServiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(service: ServiceEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(services: List<ServiceEntity>)
    
    @Update
    suspend fun update(service: ServiceEntity)
    
    @Delete
    suspend fun delete(service: ServiceEntity)
    
    @Query("SELECT * FROM services")
    fun getAllServices(): Flow<List<ServiceEntity>>
    
    @Query("SELECT * FROM services WHERE id = :serviceId")
    fun getServiceById(serviceId: Int): Flow<ServiceEntity?>
    
    @Query("DELETE FROM services WHERE id = :serviceId")
    suspend fun deleteById(serviceId: Int)
}
