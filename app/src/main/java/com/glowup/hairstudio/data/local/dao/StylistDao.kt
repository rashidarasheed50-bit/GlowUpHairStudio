package com.glowup.hairstudio.data.local.dao

import androidx.room.*
import com.glowup.hairstudio.data.local.entity.StylistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stylist: StylistEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stylists: List<StylistEntity>)
    
    @Update
    suspend fun update(stylist: StylistEntity)
    
    @Query("SELECT * FROM stylists")
    fun getAllStylists(): Flow<List<StylistEntity>>
    
    @Query("SELECT * FROM stylists WHERE id = :stylistId")
    fun getStylistById(stylistId: Int): Flow<StylistEntity?>
    
    @Delete
    suspend fun delete(stylist: StylistEntity)
}
