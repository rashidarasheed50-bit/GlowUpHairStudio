package com.glowup.hairstudio.utils

object Constants {
    // App Info
    const val APP_NAME = "GlowUp Hair Studio"
    const val APP_VERSION = "1.0.0"
    
    // Business Info
    const val SALON_ADDRESS = "123 Main Street, Austin, TX 78701"
    const val SALON_PHONE = "(512) 555-0123"
    const val SALON_EMAIL = "info@glowuphair.com"
    
    // Loyalty Program
    const val LOYALTY_VISITS_REQUIRED = 10
    
    // Time Constants
    const val REMINDER_HOURS_BEFORE = 1L
    const val BOOKING_ADVANCE_DAYS = 30
    
    // Preferences Keys
    const val PREF_CURRENT_USER_ID = "current_user_id"
    const val PREF_IS_FIRST_LAUNCH = "is_first_launch"
    const val PREF_NOTIFICATIONS_ENABLED = "notifications_enabled"
    
    // Work Manager Tags
    const val REMINDER_WORK_TAG = "appointment_reminder"
    
    // Date Format
    const val DATE_FORMAT = "MMM dd, yyyy"
    const val TIME_FORMAT = "h:mm a"
    const val DATE_TIME_FORMAT = "MMM dd, yyyy 'at' h:mm a"
    
    // Navigation Routes
    object Routes {
        const val SPLASH = "splash"
        const val ONBOARDING = "onboarding"
        const val LOGIN = "login"
        const val SIGNUP = "signup"
        const val HOME = "home"
        const val SERVICES = "services"
        const val BOOKING = "booking"
        const val APPOINTMENTS = "appointments"
        const val REVIEWS = "reviews"
        const val PROFILE = "profile"
        const val ADMIN_DASHBOARD = "admin_dashboard"
        const val ADMIN_BOOKINGS = "admin_bookings"
        const val ADMIN_CALENDAR = "admin_calendar"
        const val ADMIN_MANAGE = "admin_manage"
    }
}
