package com.glowup.hairstudio

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlowUpApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        // Application initialization
    }
}
