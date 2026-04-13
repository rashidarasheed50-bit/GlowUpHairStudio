package com.glowup.hairstudio.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.glowup.hairstudio.presentation.components.GlowUpButton
import com.glowup.hairstudio.presentation.components.GlowUpCard
import com.glowup.hairstudio.presentation.components.LoadingIndicator
import com.glowup.hairstudio.presentation.theme.*
import com.glowup.hairstudio.utils.DateTimeUtils

@Composable
fun HomeScreen(
    onNavigateToServices: () -> Unit,
    onNavigateToAppointments: () -> Unit,
    onNavigateToReviews: () -> Unit,
    onNavigateToProfile: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    if (state.isLoading) {
        LoadingIndicator()
        return
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        // Top Bar
        Surface(
            shadowElevation = Elevation.Small
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.Medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hi, Welcome! 👋",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "Book your perfect look today",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }
                
                IconButton(onClick = { /* Notifications */ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = PrimaryPink
                    )
                }
            }
        }
        
        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
        ) {
            // Loyalty Card
            state.loyaltyProgress?.let { loyalty ->
                GlowUpCard {
                    Column(
                        modifier = Modifier.padding(Spacing.Medium)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "🎉 Loyalty Rewards",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "${loyalty.currentVisits}/10",
                                style = MaterialTheme.typography.titleLarge,
                                color = PrimaryPink
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(Spacing.Small))
                        
                        LinearProgressIndicator(
                            progress = loyalty.progressPercentage / 100f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = PrimaryPink,
                            trackColor = LightPink
                        )
                        
                        Spacer(modifier = Modifier.height(Spacing.Small))
                        
                        Text(
                            text = if (loyalty.isFreeVisitEarned) {
                                "🎊 Congratulations! Free visit earned!"
                            } else {
                                "${loyalty.visitsRemaining} more visits to earn a free service"
                            },
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            
            // Upcoming Appointments
            Text(
                text = "Upcoming Appointments",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            
            if (state.upcomingAppointments.isEmpty()) {
                GlowUpCard {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Spacing.Large),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MediumGray
                        )
                        Spacer(modifier = Modifier.height(Spacing.Small))
                        Text(
                            text = "No upcoming appointments",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }
                }
            } else {
                state.upcomingAppointments.take(3).forEach { appointment ->
                    GlowUpCard {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Spacing.Medium),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = appointment.serviceName ?: "Service",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = DateTimeUtils.formatDateTime(appointment.dateTime),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextSecondary
                                )
                                Text(
                                    text = "with ${appointment.stylistName}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextSecondary
                                )
                            }
                            Text(
                                text = "$${appointment.price}",
                                style = MaterialTheme.typography.titleMedium,
                                color = PrimaryPink
                            )
                        }
                    }
                }
            }
            
            // Quick Actions
            Spacer(modifier = Modifier.height(Spacing.Small))
            
            GlowUpButton(
                text = "Browse Services & Book Now",
                onClick = onNavigateToServices
            )
            
            Spacer(modifier = Modifier.height(Spacing.ExtraLarge))
        }
    }
}
