package com.glowup.hairstudio.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {
    
    fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
    
    fun formatTime(timestamp: Long): String {
        val sdf = SimpleDateFormat(Constants.TIME_FORMAT, Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
    
    fun formatDateTime(timestamp: Long): String {
        val sdf = SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
    
    fun formatTimeAgo(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp
        
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = days / 30
        
        return when {
            months > 0 -> "$months ${if (months == 1L) "month" else "months"} ago"
            weeks > 0 -> "$weeks ${if (weeks == 1L) "week" else "weeks"} ago"
            days > 0 -> "$days ${if (days == 1L) "day" else "days"} ago"
            hours > 0 -> "$hours ${if (hours == 1L) "hour" else "hours"} ago"
            minutes > 0 -> "$minutes ${if (minutes == 1L) "minute" else "minutes"} ago"
            else -> "Just now"
        }
    }
    
    fun isToday(timestamp: Long): Boolean {
        val today = Calendar.getInstance()
        val date = Calendar.getInstance().apply { timeInMillis = timestamp }
        
        return today.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)
    }
    
    fun isTomorrow(timestamp: Long): Boolean {
        val tomorrow = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1)
        }
        val date = Calendar.getInstance().apply { timeInMillis = timestamp }
        
        return tomorrow.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                tomorrow.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)
    }
    
    fun getStartOfDay(timestamp: Long): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return calendar.timeInMillis
    }
    
    fun getEndOfDay(timestamp: Long): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }
        return calendar.timeInMillis
    }
    
    fun getAvailableTimeSlots(date: Long): List<Long> {
        val slots = mutableListOf<Long>()
        val calendar = Calendar.getInstance().apply {
            timeInMillis = date
            set(Calendar.HOUR_OF_DAY, 9) // Start at 9 AM
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        
        // Generate slots from 9 AM to 6 PM with 30-minute intervals
        val endHour = 18 // 6 PM
        while (calendar.get(Calendar.HOUR_OF_DAY) < endHour) {
            slots.add(calendar.timeInMillis)
            calendar.add(Calendar.MINUTE, 30)
        }
        
        return slots
    }
}
