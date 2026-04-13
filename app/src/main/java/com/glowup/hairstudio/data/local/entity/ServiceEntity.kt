package com.glowup.hairstudio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glowup.hairstudio.domain.model.Service

@Entity(tableName = "services")
data class ServiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val price: Double,
    val duration: Int, // in minutes
    val imageUrl: String? = null
)

// Mapper functions
fun ServiceEntity.toDomain(): Service {
    return Service(
        id = id,
        name = name,
        description = description,
        price = price,
        duration = duration,
        imageUrl = imageUrl
    )
}

fun Service.toEntity(): ServiceEntity {
    return ServiceEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        duration = duration,
        imageUrl = imageUrl
    )
}
