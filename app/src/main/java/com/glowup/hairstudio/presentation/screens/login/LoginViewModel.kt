package com.glowup.hairstudio.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glowup.hairstudio.domain.model.User
import com.glowup.hairstudio.domain.usecase.auth.LoginUseCase
import com.glowup.hairstudio.utils.ValidationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()
    
    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email, emailError = null, errorMessage = null) }
    }
    
    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password, passwordError = null, errorMessage = null) }
    }
    
    fun onLoginClick() {
        // Validate inputs
        val emailError = ValidationUtils.getEmailError(_state.value.email)
        val passwordError = ValidationUtils.getPasswordError(_state.value.password)
        
        if (emailError != null || passwordError != null) {
            _state.update {
                it.copy(
                    emailError = emailError,
                    passwordError = passwordError
                )
            }
            return
        }
        
        // Proceed with login
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            
            val result = loginUseCase(
                email = _state.value.email,
                password = _state.value.password
            )
            
            result.fold(
                onSuccess = { user ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                },
                onFailure = { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.message ?: "Login failed"
                        )
                    }
                }
            )
        }
    }
}
