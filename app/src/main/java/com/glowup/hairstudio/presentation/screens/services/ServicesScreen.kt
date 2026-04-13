package com.glowup.hairstudio.presentation.screens.services

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.glowup.hairstudio.presentation.components.LoadingIndicator
import com.glowup.hairstudio.presentation.components.ServiceCard
import com.glowup.hairstudio.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(
    onNavigateBack: () -> Unit,
    onBookService: (Int) -> Unit,
    viewModel: ServicesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Our Services") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = White,
                    titleContentColor = TextPrimary,
                    navigationIconContentColor = PrimaryPink
                )
            )
        }
    ) { padding ->
        if (state.isLoading) {
            LoadingIndicator()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundLight)
                    .padding(padding)
                    .padding(Spacing.Medium),
                verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
            ) {
                item {
                    Text(
                        text = "Choose your perfect service",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(Spacing.Small))
                }
                
                items(state.services) { service ->
                    ServiceCard(
                        service = service,
                        onBookClick = { onBookService(service.id) }
                    )
                }
                
                item {
                    Spacer(modifier = Modifier.height(Spacing.Large))
                }
            }
        }
    }
}
