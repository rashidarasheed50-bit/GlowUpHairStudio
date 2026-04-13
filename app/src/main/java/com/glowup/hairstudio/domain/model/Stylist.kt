package com.glowup.hairstudio.domain.model

data class Stylist(
    val id: Int,
    val name: String,
    val specialty: String,
    val rating: Double,
    val imageUrl: String? = null,
    val bio: String? = null
)
