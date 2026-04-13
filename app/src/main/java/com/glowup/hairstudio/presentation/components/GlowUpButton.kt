package com.glowup.hairstudio.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.glowup.hairstudio.presentation.theme.*

@Composable
fun GlowUpButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    isPrimary: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        enabled = enabled && !isLoading,
        shape = RoundedCornerShape(CornerRadius.Medium),
        colors = if (isPrimary) {
            ButtonDefaults.buttonColors(
                containerColor = PrimaryPink,
                contentColor = White,
                disabledContainerColor = MediumGray,
                disabledContentColor = DarkGray
            )
        } else {
            ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = PrimaryPink,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = DarkGray
            )
        },
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isPrimary) Elevation.Medium else Elevation.None
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = White,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun GlowUpOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        enabled = enabled,
        shape = RoundedCornerShape(CornerRadius.Medium),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = PrimaryPink
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush = androidx.compose.ui.graphics.SolidColor(PrimaryPink)
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(color = PrimaryPink)
        )
    }
}
