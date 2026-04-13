package com.glowup.hairstudio.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.glowup.hairstudio.presentation.theme.PrimaryPink
import com.glowup.hairstudio.presentation.theme.Spacing
import com.glowup.hairstudio.presentation.theme.White

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val navigateToLogin by viewModel.navigateToLogin.collectAsState()
    val navigateToHome by viewModel.navigateToHome.collectAsState()
    
    LaunchedEffect(navigateToLogin) {
        if (navigateToLogin) {
            onNavigateToLogin()
        }
    }
    
    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            onNavigateToHome()
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryPink),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.Large)
        ) {
            Text(
                text = "💇",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 80.dp.value.toInt().sp
                )
            )
            
            Text(
                text = "GlowUp",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            )
            
            Text(
                text = "Hair Studio",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = White
                )
            )
            
            Spacer(modifier = Modifier.height(Spacing.ExtraLarge))
            
            CircularProgressIndicator(
                color = White,
                strokeWidth = 3.dp
            )
        }
    }
}
