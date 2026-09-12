@file:Suppress("DEPRECATION")

package com.netguru.multiplatform.charts.dial

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netguru.multiplatform.charts.ChartAnimation
import com.netguru.multiplatform.charts.toCurrent
import io.github.gurgenky.charts.dial.dialColors
import io.github.gurgenky.charts.theme.ChartTheme

@Deprecated("Use io.github.gurgenky.charts.dial.DialConfig")
typealias DialConfig = io.github.gurgenky.charts.dial.DialConfig

@Deprecated("Use io.github.gurgenky.charts.dial.DialColors")
typealias DialColors = io.github.gurgenky.charts.dial.DialColors

@Deprecated("Use io.github.gurgenky.charts.dial.DialJoinStyle")
typealias DialJoinStyle = io.github.gurgenky.charts.dial.DialJoinStyle

@Deprecated("Use io.github.gurgenky.charts.dial.Dial")
@Composable
fun Dial(
    value: Int,
    minValue: Int,
    maxValue: Int,
    modifier: Modifier = Modifier,
    animation: ChartAnimation = ChartAnimation.Simple(),
    colors: DialColors = ChartTheme.colors.dialColors,
    config: DialConfig = DialConfig(),
    minAndMaxValueLabel: @Composable (Int) -> Unit = { Text(it.toString()) },
    mainLabel: @Composable (Int) -> Unit = { Text(it.toString()) },
) = io.github.gurgenky.charts.dial.Dial(
    value, minValue, maxValue, modifier, animation.toCurrent(), colors, config, minAndMaxValueLabel, mainLabel,
)

@Deprecated("Use io.github.gurgenky.charts.dial.PercentageDial")
@Composable
fun PercentageDial(
    percentage: Int,
    modifier: Modifier = Modifier,
    animation: ChartAnimation = ChartAnimation.Simple(),
    colors: DialColors = ChartTheme.colors.dialColors,
    config: DialConfig = DialConfig(),
    minAndMaxValueLabel: @Composable (Int) -> Unit = { Text(it.toString()) },
    mainLabel: @Composable (Int) -> Unit = { Text("$it%") },
) = io.github.gurgenky.charts.dial.PercentageDial(
    percentage, modifier, animation.toCurrent(), colors, config, minAndMaxValueLabel, mainLabel,
)
