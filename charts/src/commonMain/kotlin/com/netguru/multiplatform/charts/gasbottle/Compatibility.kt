@file:Suppress("DEPRECATION")

package com.netguru.multiplatform.charts.gasbottle

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netguru.multiplatform.charts.ChartAnimation
import com.netguru.multiplatform.charts.toCurrent
import io.github.gurgenky.charts.gasbottle.gasBottleColors
import io.github.gurgenky.charts.theme.ChartTheme

@Deprecated("Use io.github.gurgenky.charts.gasbottle.GasBottleColors")
typealias GasBottleColors = io.github.gurgenky.charts.gasbottle.GasBottleColors

@Deprecated("Use io.github.gurgenky.charts.gasbottle.GasBottle")
@Composable
fun GasBottle(
    percentage: Float,
    modifier: Modifier = Modifier,
    animation: ChartAnimation = ChartAnimation.Simple(),
    colors: GasBottleColors = ChartTheme.colors.gasBottleColors,
) = io.github.gurgenky.charts.gasbottle.GasBottle(percentage, modifier, animation.toCurrent(), colors)
