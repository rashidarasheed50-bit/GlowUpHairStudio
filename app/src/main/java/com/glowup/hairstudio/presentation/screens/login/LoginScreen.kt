package com.glowup.hairstudio.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.glowup.hairstudio.presentation.components.GlowUpButton
import com.glowup.hairstudio.presentation.components.GlowUpTextField
import com.glowup.hairstudio.presentation.theme.*

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onNavigateToHome()
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Spacing.Large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(Spacing.ExtraExtraLarge))
            
            // Logo
            Text(
                text = "💇",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 60.dp.value.toInt().sp
                )
            )
            
            Spacer(modifier = Modifier.height(Spacing.Medium))
            
            Text(
                text = "Welcome Back! 💖",
                style = MaterialTheme.typography.displayMedium
            )
            
            Text(
                text = "Login to your account",
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )
            
            Spacer(modifier = Modifier.height(Spacing.ExtraLarge))
            
            // Demo credentials hint
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = LightPink
                )
            ) {
                Column(
                    modifier = Modifier.padding(Spacing.Medium)
                ) {
                    Text(
                        text = "Demo Credentials:",
                        style = MaterialTheme.typography.titleSmall,
                        color = DarkPink
                    )
                    Spacer(modifier = Modifier.height(Spacing.Small))
                    Text(
                        text = "Customer: customer@demo.com / demo123",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Admin: admin@demo.com / admin123",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(Spacing.Large))
            
            // Email Field
            GlowUpTextField(
                value = state.email,
                onValueChange = viewModel::onEmailChange,
                label = "Email",
                placeholder = "Enter your email",
                leadingIcon = Icons.Default.Email,
                isError = state.emailError != null,
                errorMessage = state.emailError,
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            
            Spacer(modifier = Modifier.height(Spacing.Medium))
            
            // Password Field
            GlowUpTextField(
                value = state.password,
                onValueChange = viewModel::onPasswordChange,
                label = "Password",
                placeholder = "Enter your password",
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                isError = state.passwordError != null,
                errorMessage = state.passwordError
            )
            
            Spacer(modifier = Modifier.height(Spacing.Large))
            
            // Error Message
            if (state.errorMessage != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Error.copy(alpha = 0.1f)
                    )
                ) {
                    Text(
                        text = state.errorMessage ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Error,
                        modifier = Modifier.padding(Spacing.Medium)
                    )
                }
                
                Spacer(modifier = Modifier.height(Spacing.Medium))
            }
            
            // Login Button
            GlowUpButton(
                text = "Login",
                onClick = viewModel::onLoginClick,
                isLoading = state.isLoading
            )
            
            Spacer(modifier = Modifier.height(Spacing.Medium))
            
            // Sign Up Link
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account? ",
                    style = MaterialTheme.typography.bodyMedium
                )
                TextButton(onClick = onNavigateToSignUp) {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.bodyMedium,
                        color = PrimaryPink
                    )
                }
            }
        }
    }
}
