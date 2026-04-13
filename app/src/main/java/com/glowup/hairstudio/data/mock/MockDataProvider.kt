package com.glowup.hairstudio.data.mock

import com.glowup.hairstudio.data.local.entity.*
import com.glowup.hairstudio.domain.model.UserRole

object MockDataProvider {
    
    // Demo Users
    val demoCustomer = UserEntity(
        id = 1,
        email = "customer@demo.com",
        password = "demo123",
        fullName = "Sarah Williams",
        phone = "555-0123",
        role = UserRole.CUSTOMER.name,
        loyaltyVisits = 7
    )
    
    val demoAdmin = UserEntity(
        id = 2,
        email = "admin@demo.com",
        password = "admin123",
        fullName = "Admin User",
        phone = "555-9999",
        role = UserRole.ADMIN.name,
        loyaltyVisits = 0
    )
    
    fun getAllDemoUsers() = listOf(demoCustomer, demoAdmin)
    
    // Services
    fun getAllServices() = listOf(
        ServiceEntity(
            id = 1,
            name = "Haircut",
            description = "Professional haircut and styling",
            price = 45.0,
            duration = 45,
            imageUrl = "💇"
        ),
        ServiceEntity(
            id = 2,
            name = "Hair Color",
            description = "Full color service with premium products",
            price = 120.0,
            duration = 120,
            imageUrl = "🎨"
        ),
        ServiceEntity(
            id = 3,
            name = "Highlights",
            description = "Partial or full highlights",
            price = 85.0,
            duration = 90,
            imageUrl = "✨"
        ),
        ServiceEntity(
            id = 4,
            name = "Blowout",
            description = "Professional blowout and styling",
            price = 35.0,
            duration = 30,
            imageUrl = "💨"
        ),
        ServiceEntity(
            id = 5,
            name = "Deep Conditioning",
            description = "Intensive hair treatment and conditioning",
            price = 40.0,
            duration = 45,
            imageUrl = "🧴"
        ),
        ServiceEntity(
            id = 6,
            name = "Keratin Treatment",
            description = "Smoothing keratin treatment",
            price = 200.0,
            duration = 180,
            imageUrl = "💆"
        )
    )
    
    // Stylists
    fun getAllStylists() = listOf(
        StylistEntity(
            id = 1,
            name = "Emma Johnson",
            specialty = "Color Specialist",
            rating = 4.9,
            imageUrl = "👩",
            bio = "10+ years of experience in hair coloring and balayage techniques"
        ),
        StylistEntity(
            id = 2,
            name = "Lisa Martinez",
            specialty = "Cut & Style Expert",
            rating = 4.8,
            imageUrl = "👩",
            bio = "Specializes in modern haircuts and precision styling"
        ),
        StylistEntity(
            id = 3,
            name = "Anna Chen",
            specialty = "Master Stylist",
            rating = 5.0,
            imageUrl = "👩",
            bio = "Award-winning stylist with expertise in all services"
        )
    )
    
    // Sample appointments for demo customer
    fun getSampleAppointments(userId: Int = 1): List<AppointmentEntity> {
        val now = System.currentTimeMillis()
        val oneDay = 24 * 60 * 60 * 1000L
        
        return listOf(
            // Upcoming appointment
            AppointmentEntity(
                id = 1,
                userId = userId,
                serviceId = 1,
                stylistId = 1,
                dateTime = now + (5 * oneDay), // 5 days from now
                duration = 45,
                price = 45.0,
                status = "CONFIRMED",
                specialRequests = "Please trim bangs lightly"
            ),
            // Past appointment
            AppointmentEntity(
                id = 2,
                userId = userId,
                serviceId = 2,
                stylistId = 2,
                dateTime = now - (10 * oneDay), // 10 days ago
                duration = 120,
                price = 120.0,
                status = "COMPLETED",
                specialRequests = null
            ),
            // Another upcoming
            AppointmentEntity(
                id = 3,
                userId = userId,
                serviceId = 4,
                stylistId = 3,
                dateTime = now + (12 * oneDay), // 12 days from now
                duration = 30,
                price = 35.0,
                status = "PENDING",
                specialRequests = null
            )
        )
    }
    
    // Sample reviews
    fun getSampleReviews(userId: Int = 1): List<ReviewEntity> {
        return listOf(
            ReviewEntity(
                id = 1,
                userId = userId,
                appointmentId = 2,
                stylistId = 2,
                rating = 5,
                comment = "Amazing service! Lisa did an incredible job with my hair color. Highly recommend!",
                createdAt = System.currentTimeMillis() - (10 * 24 * 60 * 60 * 1000L)
            ),
            ReviewEntity(
                id = 2,
                userId = userId,
                appointmentId = 4,
                stylistId = 1,
                rating = 5,
                comment = "Emma is a true professional. Best haircut I've ever had!",
                createdAt = System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000L)
            )
        )
    }
}
