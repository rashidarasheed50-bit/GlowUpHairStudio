package com.glowup.hairstudio.di

import com.glowup.hairstudio.data.repository.*
import com.glowup.hairstudio.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
    
    @Binds
    @Singleton
    abstract fun bindServiceRepository(
        serviceRepositoryImpl: ServiceRepositoryImpl
    ): ServiceRepository
    
    @Binds
    @Singleton
    abstract fun bindStylistRepository(
        stylistRepositoryImpl: StylistRepositoryImpl
    ): StylistRepository
    
    @Binds
    @Singleton
    abstract fun bindAppointmentRepository(
        appointmentRepositoryImpl: AppointmentRepositoryImpl
    ): AppointmentRepository
    
    @Binds
    @Singleton
    abstract fun bindReviewRepository(
        reviewRepositoryImpl: ReviewRepositoryImpl
    ): ReviewRepository
}
