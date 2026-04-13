package com.glowup.hairstudio.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.glowup.hairstudio.data.local.dao.*
import com.glowup.hairstudio.data.local.entity.*

@Database(
    entities = [
        UserEntity::class,
        ServiceEntity::class,
        StylistEntity::class,
        AppointmentEntity::class,
        ReviewEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GlowUpDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun serviceDao(): ServiceDao
    abstract fun stylistDao(): StylistDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun reviewDao(): ReviewDao
    
    companion object {
        const val DATABASE_NAME = "glowup_database"
    }
}
