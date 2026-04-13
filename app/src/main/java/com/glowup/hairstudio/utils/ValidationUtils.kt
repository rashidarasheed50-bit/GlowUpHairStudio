package com.glowup.hairstudio.utils

import android.util.Patterns

object ValidationUtils {
    
    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
    
    fun isValidPhone(phone: String): Boolean {
        // Simple validation - at least 10 digits
        val digitsOnly = phone.filter { it.isDigit() }
        return digitsOnly.length >= 10
    }
    
    fun isValidName(name: String): Boolean {
        return name.isNotBlank() && name.length >= 2
    }
    
    fun getEmailError(email: String): String? {
        return when {
            email.isBlank() -> "Email cannot be empty"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email format"
            else -> null
        }
    }
    
    fun getPasswordError(password: String): String? {
        return when {
            password.isBlank() -> "Password cannot be empty"
            password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }
    }
    
    fun getPhoneError(phone: String): String? {
        return when {
            phone.isBlank() -> "Phone number cannot be empty"
            phone.filter { it.isDigit() }.length < 10 -> "Invalid phone number"
            else -> null
        }
    }
    
    fun getNameError(name: String): String? {
        return when {
            name.isBlank() -> "Name cannot be empty"
            name.length < 2 -> "Name must be at least 2 characters"
            else -> null
        }
    }
}
