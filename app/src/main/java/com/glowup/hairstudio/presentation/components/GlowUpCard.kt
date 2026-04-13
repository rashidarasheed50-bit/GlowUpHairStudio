package com.glowup.hairstudio.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.glowup.hairstudio.presentation.theme.*

@Composable
fun GlowUpCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick ?: {},
        shape = RoundedCornerShape(CornerRadius.Large),
        colors = CardDefaults.cardColors(
            containerColor = White,
            contentColor = TextPrimary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Elevation.Small
        )
    ) {
        content()
    }
}
