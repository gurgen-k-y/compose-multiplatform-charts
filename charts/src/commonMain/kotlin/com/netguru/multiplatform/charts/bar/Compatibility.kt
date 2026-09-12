@file:Suppress("DEPRECATION")

package com.netguru.multiplatform.charts.bar

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netguru.multiplatform.charts.ChartAnimation
import com.netguru.multiplatform.charts.toCurrent
import io.github.gurgenky.charts.theme.ChartTheme
import io.github.gurgenky.charts.bar.barChartColors

@Deprecated("Use io.github.gurgenky.charts.bar.BarChartData")
typealias BarChartData = io.github.gurgenky.charts.bar.BarChartData

@Deprecated("Use io.github.gurgenky.charts.bar.BarChartCategory")
typealias BarChartCategory = io.github.gurgenky.charts.bar.BarChartCategory

@Deprecated("Use io.github.gurgenky.charts.bar.BarChartEntry")
typealias BarChartEntry = io.github.gurgenky.charts.bar.BarChartEntry

@Deprecated("Use io.github.gurgenky.charts.bar.BarChartConfig")
typealias BarChartConfig = io.github.gurgenky.charts.bar.BarChartConfig

@Deprecated("Use io.github.gurgenky.charts.bar.BarChartColors")
typealias BarChartColors = io.github.gurgenky.charts.bar.BarChartColors

@Deprecated("Use io.github.gurgenky.charts.bar.BarChart")
@Composable
fun BarChart(
    data: BarChartData,
    modifier: Modifier = Modifier,
    colors: BarChartColors = ChartTheme.colors.barChartColors,
    config: BarChartConfig = BarChartConfig(),
    xAxisLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    yAxisLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    animation: ChartAnimation = ChartAnimation.Simple(),
    overlayDataEntryLabel: @Composable (String, Any) -> Unit = { name, value -> Text("$name: $value") },
) = io.github.gurgenky.charts.bar.BarChart(
    data = data,
    modifier = modifier,
    colors = colors,
    config = config,
    xAxisLabel = xAxisLabel,
    yAxisLabel = yAxisLabel,
    animation = animation.toCurrent(),
    overlayDataEntryLabel = overlayDataEntryLabel,
)

@Deprecated("Use io.github.gurgenky.charts.bar.BarChartWithLegend")
@Composable
fun BarChartWithLegend(
    data: BarChartData,
    modifier: Modifier = Modifier,
    animation: ChartAnimation = ChartAnimation.Simple(),
    colors: BarChartColors = ChartTheme.colors.barChartColors,
    config: BarChartConfig = BarChartConfig(),
    xAxisLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    yAxisLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    legendItemLabel: @Composable (String) -> Unit = { Text(it) },
) = io.github.gurgenky.charts.bar.BarChartWithLegend(
    data = data,
    modifier = modifier,
    animation = animation.toCurrent(),
    colors = colors,
    config = config,
    xAxisLabel = xAxisLabel,
    yAxisLabel = yAxisLabel,
    legendItemLabel = legendItemLabel,
)
