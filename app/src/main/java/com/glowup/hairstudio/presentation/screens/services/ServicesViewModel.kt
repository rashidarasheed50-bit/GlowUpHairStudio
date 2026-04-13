package com.glowup.hairstudio.presentation.screens.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glowup.hairstudio.domain.model.Service
import com.glowup.hairstudio.domain.usecase.service.GetAllServicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ServicesState(
    val services: List<Service> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

@HiltViewModel
class ServicesViewModel @Inject constructor(
    private val getAllServicesUseCase: GetAllServicesUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(ServicesState())
    val state = _state.asStateFlow()
    
    init {
        loadServices()
    }
    
    private fun loadServices() {
        viewModelScope.launch {
            try {
                getAllServicesUseCase().collect { services ->
                    _state.update {
                        it.copy(
                            services = services,
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to load services"
                    )
                }
            }
        }
    }
}
