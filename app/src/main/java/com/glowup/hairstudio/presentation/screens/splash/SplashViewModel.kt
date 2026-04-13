package com.glowup.hairstudio.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glowup.hairstudio.utils.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {
    
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    
    private val _navigateToLogin = MutableStateFlow(false)
    val navigateToLogin = _navigateToLogin.asStateFlow()
    
    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome = _navigateToHome.asStateFlow()
    
    init {
        checkAuthStatus()
    }
    
    private fun checkAuthStatus() {
        viewModelScope.launch {
            // Show splash for minimum 2 seconds
            delay(2000)
            
            val userId = preferenceManager.getCurrentUserId()
            
            if (userId != -1) {
                // User is logged in
                _navigateToHome.value = true
            } else {
                // User not logged in
                _navigateToLogin.value = true
            }
            
            _isLoading.value = false
        }
    }
}
