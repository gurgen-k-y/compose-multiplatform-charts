package io.github.gurgenky.charts.line

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import io.github.gurgenky.charts.theme.ChartColors

@Immutable
data class LineChartColors(
    val grid: Color,
    val surface: Color,
    val overlayLine: Color,
)

val ChartColors.lineChartColors
    get() = LineChartColors(
        grid = grid,
        surface = surface,
        overlayLine = overlayLine,
    )
