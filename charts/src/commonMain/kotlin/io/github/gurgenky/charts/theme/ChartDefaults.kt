package io.github.gurgenky.charts.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Contains the default values used by all charts
 */
object ChartDefaults {
    @Composable
    fun chartColors(
        primary: Color = MaterialTheme.colors.primary,
        surface: Color = Color.Unspecified,
        grid: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
        emptyGasBottle: Color = MaterialTheme.colors.error,
        fullGasBottle: Color = MaterialTheme.colors.primary,
        overlayLine: Color = MaterialTheme.colors.error,
    ) = ChartColors(
        primary = primary,
        surface = surface,
        grid = grid,
        emptyGasBottle = emptyGasBottle,
        fullGasBottle = fullGasBottle,
        overlayLine = overlayLine,
    )

    fun lightColors(
        primary: Color = Color(0xFF2563EB),
        surface: Color = Color.White,
        grid: Color = Color(0xFFD1D5DB),
        error: Color = Color(0xFFDC2626),
    ) = ChartColors(
        primary = primary,
        surface = surface,
        grid = grid,
        emptyGasBottle = error,
        fullGasBottle = primary,
        overlayLine = error,
        axis = Color(0xFF6B7280),
        label = Color(0xFF111827),
        tooltipBackground = Color(0xFF111827),
        tooltipContent = Color.White,
    )

    fun darkColors(
        primary: Color = Color(0xFF60A5FA),
        surface: Color = Color(0xFF111827),
        grid: Color = Color(0xFF374151),
        error: Color = Color(0xFFF87171),
    ) = ChartColors(
        primary = primary,
        surface = surface,
        grid = grid,
        emptyGasBottle = error,
        fullGasBottle = primary,
        overlayLine = error,
        axis = Color(0xFF9CA3AF),
        label = Color(0xFFF9FAFB),
        tooltipBackground = Color(0xFFF9FAFB),
        tooltipContent = Color(0xFF111827),
    )
}
