package io.github.gurgenky.charts.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalChartTheme = staticCompositionLocalOf {
    ChartDefaults.lightColors()
}

object ChartTheme {
    val colors: ChartColors
        @Composable
        @ReadOnlyComposable
        get() = LocalChartTheme.current

    @Composable
    operator fun invoke(
        colors: ChartColors = if (isSystemInDarkTheme()) {
            ChartDefaults.darkColors()
        } else {
            ChartDefaults.lightColors()
        },
        content: @Composable () -> Unit,
    ) {
        CompositionLocalProvider(LocalChartTheme provides colors, content = content)
    }
}

@Deprecated("Use LocalChartTheme", ReplaceWith("LocalChartTheme"))
val LocalChartColors = LocalChartTheme
