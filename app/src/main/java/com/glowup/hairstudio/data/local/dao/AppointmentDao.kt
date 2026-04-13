package com.glowup.hairstudio.data.local.dao

import androidx.room.*
import com.glowup.hairstudio.data.local.entity.AppointmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appointment: AppointmentEntity): Long
    
    @Update
    suspend fun update(appointment: AppointmentEntity)
    
    @Delete
    suspend fun delete(appointment: AppointmentEntity)
    
    @Query("SELECT * FROM appointments WHERE userId = :userId ORDER BY dateTime DESC")
    fun getAppointmentsByUserId(userId: Int): Flow<List<AppointmentEntity>>
    
    @Query("SELECT * FROM appointments ORDER BY dateTime DESC")
    fun getAllAppointments(): Flow<List<AppointmentEntity>>
    
    @Query("SELECT * FROM appointments WHERE id = :appointmentId")
    fun getAppointmentById(appointmentId: Int): Flow<AppointmentEntity?>
    
    @Query("UPDATE appointments SET status = :status WHERE id = :appointmentId")
    suspend fun updateStatus(appointmentId: Int, status: String)
    
    @Query("SELECT * FROM appointments WHERE userId = :userId AND dateTime > :currentTime AND status != 'CANCELLED' ORDER BY dateTime ASC")
    fun getUpcomingAppointments(userId: Int, currentTime: Long): Flow<List<AppointmentEntity>>
    
    @Query("SELECT * FROM appointments WHERE userId = :userId AND dateTime < :currentTime ORDER BY dateTime DESC")
    fun getPastAppointments(userId: Int, currentTime: Long): Flow<List<AppointmentEntity>>
    
    @Query("SELECT * FROM appointments WHERE dateTime >= :startOfDay AND dateTime < :endOfDay ORDER BY dateTime ASC")
    fun getTodayAppointments(startOfDay: Long, endOfDay: Long): Flow<List<AppointmentEntity>>
    
    @Query("DELETE FROM appointments WHERE id = :appointmentId")
    suspend fun deleteById(appointmentId: Int)
}
