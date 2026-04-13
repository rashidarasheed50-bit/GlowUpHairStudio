package com.glowup.hairstudio.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glowup.hairstudio.domain.model.Appointment
import com.glowup.hairstudio.domain.model.LoyaltyProgress
import com.glowup.hairstudio.domain.usecase.appointment.GetAppointmentsUseCase
import com.glowup.hairstudio.domain.usecase.loyalty.GetLoyaltyProgressUseCase
import com.glowup.hairstudio.utils.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val userName: String = "",
    val upcomingAppointments: List<Appointment> = emptyList(),
    val loyaltyProgress: LoyaltyProgress? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAppointmentsUseCase: GetAppointmentsUseCase,
    private val getLoyaltyProgressUseCase: GetLoyaltyProgressUseCase,
    private val preferenceManager: PreferenceManager
) : ViewModel() {
    
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            try {
                val userId = preferenceManager.getCurrentUserId()
                
                // Collect appointments
                launch {
                    getAppointmentsUseCase(userId).collect { appointments ->
                        val upcoming = appointments.filter {
                            it.dateTime > System.currentTimeMillis() &&
                            it.status.name != "CANCELLED"
                        }.sortedBy { it.dateTime }
                        
                        _state.update { it.copy(upcomingAppointments = upcoming) }
                    }
                }
                
                // Collect loyalty progress
                launch {
                    getLoyaltyProgressUseCase(userId).collect { loyalty ->
                        _state.update { it.copy(loyaltyProgress = loyalty) }
                    }
                }
                
                _state.update { it.copy(isLoading = false) }
                
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to load data"
                    )
                }
            }
        }
    }
}
