package com.glowup.hairstudio.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    
    // Customer screens
    object Home : Screen("home")
    object Services : Screen("services")
    object Booking : Screen("booking/{serviceId}") {
        fun createRoute(serviceId: Int) = "booking/$serviceId"
    }
    object Appointments : Screen("appointments")
    object Reviews : Screen("reviews")
    object Profile : Screen("profile")
    
    // Admin screens
    object AdminDashboard : Screen("admin_dashboard")
    object AdminBookings : Screen("admin_bookings")
    object AdminCalendar : Screen("admin_calendar")
    object AdminManage : Screen("admin_manage")
}
