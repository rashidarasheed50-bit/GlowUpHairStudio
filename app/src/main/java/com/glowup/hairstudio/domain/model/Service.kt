package com.glowup.hairstudio.domain.model

data class Service(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val duration: Int, // in minutes
    val imageUrl: String? = null
)
