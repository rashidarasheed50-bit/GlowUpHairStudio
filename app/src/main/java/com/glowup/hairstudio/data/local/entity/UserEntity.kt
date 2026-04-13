package com.glowup.hairstudio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.glowup.hairstudio.domain.model.User
import com.glowup.hairstudio.domain.model.UserRole

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val password: String,
    val fullName: String,
    val phone: String,
    val role: String, // "CUSTOMER" or "ADMIN"
    val loyaltyVisits: Int = 0,
    val profileImageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

// Mapper functions
fun UserEntity.toDomain(): User {
    return User(
        id = id,
        email = email,
        password = password,
        fullName = fullName,
        phone = phone,
        role = UserRole.valueOf(role),
        loyaltyVisits = loyaltyVisits,
        profileImageUrl = profileImageUrl,
        createdAt = createdAt
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        email = email,
        password = password,
        fullName = fullName,
        phone = phone,
        role = role.name,
        loyaltyVisits = loyaltyVisits,
        profileImageUrl = profileImageUrl,
        createdAt = createdAt
    )
}
