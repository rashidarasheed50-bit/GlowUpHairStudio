package com.glowup.hairstudio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glowup.hairstudio.domain.model.Stylist

@Entity(tableName = "stylists")
data class StylistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val specialty: String,
    val rating: Double,
    val imageUrl: String? = null,
    val bio: String? = null
)

// Mapper functions
fun StylistEntity.toDomain(): Stylist {
    return Stylist(
        id = id,
        name = name,
        specialty = specialty,
        rating = rating,
        imageUrl = imageUrl,
        bio = bio
    )
}

fun Stylist.toEntity(): StylistEntity {
    return StylistEntity(
        id = id,
        name = name,
        specialty = specialty,
        rating = rating,
        imageUrl = imageUrl,
        bio = bio
    )
}
