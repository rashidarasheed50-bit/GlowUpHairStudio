package com.glowup.hairstudio.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.glowup.hairstudio.domain.model.Service
import com.glowup.hairstudio.presentation.theme.Spacing

@Composable
fun ServiceCard(
    service: Service,
    onBookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    GlowUpCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onBookClick
    ) {
        Column(
            modifier = Modifier.padding(Spacing.Medium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
                    ) {
                        Text(
                            text = service.imageUrl ?: "💇",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = service.name,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(Spacing.ExtraSmall))
                    
                    Text(
                        text = service.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(Spacing.Medium))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "$${service.price}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${service.duration} min",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                
                GlowUpButton(
                    text = "Book Now",
                    onClick = onBookClick,
                    modifier = Modifier.width(120.dp)
                )
            }
        }
    }
}
