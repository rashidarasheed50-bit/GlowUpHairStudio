package com.glowup.hairstudio.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.glowup.hairstudio.data.local.dao.*
import com.glowup.hairstudio.data.local.database.GlowUpDatabase
import com.glowup.hairstudio.data.mock.MockDataProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        provider: Provider<ServiceDao>,
        providerStylist: Provider<StylistDao>,
        providerUser: Provider<UserDao>,
        providerAppointment: Provider<AppointmentDao>
    ): GlowUpDatabase {
        return Room.databaseBuilder(
            context,
            GlowUpDatabase::class.java,
            GlowUpDatabase.DATABASE_NAME
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Prepopulate database with mock data
                    CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
                        val serviceDao = provider.get()
                        val stylistDao = providerStylist.get()
                        val userDao = providerUser.get()
                        val appointmentDao = providerAppointment.get()
                        
                        // Insert demo users
                        MockDataProvider.getAllDemoUsers().forEach { user ->
                            userDao.insert(user)
                        }
                        
                        // Insert services
                        serviceDao.insertAll(MockDataProvider.getAllServices())
                        
                        // Insert stylists
                        stylistDao.insertAll(MockDataProvider.getAllStylists())
                        
                        // Insert sample appointments for demo customer
                        MockDataProvider.getSampleAppointments().forEach { appointment ->
                            appointmentDao.insert(appointment)
                        }
                    }
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }
    
    @Provides
    fun provideUserDao(database: GlowUpDatabase): UserDao {
        return database.userDao()
    }
    
    @Provides
    fun provideServiceDao(database: GlowUpDatabase): ServiceDao {
        return database.serviceDao()
    }
    
    @Provides
    fun provideStylistDao(database: GlowUpDatabase): StylistDao {
        return database.stylistDao()
    }
    
    @Provides
    fun provideAppointmentDao(database: GlowUpDatabase): AppointmentDao {
        return database.appointmentDao()
    }
    
    @Provides
    fun provideReviewDao(database: GlowUpDatabase): ReviewDao {
        return database.reviewDao()
    }
}
