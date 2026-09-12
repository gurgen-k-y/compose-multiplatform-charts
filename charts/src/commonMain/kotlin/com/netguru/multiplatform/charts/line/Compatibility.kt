@file:Suppress("DEPRECATION")

package com.netguru.multiplatform.charts.line

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netguru.multiplatform.charts.ChartAnimation
import com.netguru.multiplatform.charts.toCurrent
import io.github.gurgenky.charts.line.lineChartColors
import io.github.gurgenky.charts.theme.ChartTheme

@Deprecated("Use io.github.gurgenky.charts.line.LineChartPoint")
typealias LineChartPoint = io.github.gurgenky.charts.line.LineChartPoint

@Deprecated("Use io.github.gurgenky.charts.line.LineChartSeries")
typealias LineChartSeries = io.github.gurgenky.charts.line.LineChartSeries

@Deprecated("Use io.github.gurgenky.charts.line.LineChartData")
typealias LineChartData = io.github.gurgenky.charts.line.LineChartData

@Deprecated("Use io.github.gurgenky.charts.line.LineChartColors")
typealias LineChartColors = io.github.gurgenky.charts.line.LineChartColors

@Deprecated("Use io.github.gurgenky.charts.line.LegendItemData")
typealias LegendItemData = io.github.gurgenky.charts.line.LegendItemData

@Deprecated("Use io.github.gurgenky.charts.line.SymbolShape")
typealias SymbolShape = io.github.gurgenky.charts.line.SymbolShape

@Deprecated("Use io.github.gurgenky.charts.line.PointF")
typealias PointF = io.github.gurgenky.charts.line.PointF

@Deprecated("Use io.github.gurgenky.charts.line.LineChart")
@Composable
fun LineChart(
    lineChartData: LineChartData,
    modifier: Modifier = Modifier,
    colors: LineChartColors = ChartTheme.colors.lineChartColors,
    xAxisLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    yAxisLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    overlayHeaderLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    overlayDataEntryLabel: @Composable (String, Any) -> Unit = { name, value -> Text("$name: $value") },
    animation: ChartAnimation = ChartAnimation.Simple(),
    maxVerticalLines: Int = 10,
    maxHorizontalLines: Int = 10,
    roundMinMaxClosestTo: Int = 10,
) = io.github.gurgenky.charts.line.LineChart(
    lineChartData = lineChartData,
    modifier = modifier,
    colors = colors,
    xAxisLabel = xAxisLabel,
    yAxisLabel = yAxisLabel,
    overlayHeaderLabel = overlayHeaderLabel,
    overlayDataEntryLabel = overlayDataEntryLabel,
    animation = animation.toCurrent(),
    maxVerticalLines = maxVerticalLines,
    maxHorizontalLines = maxHorizontalLines,
    roundMinMaxClosestTo = roundMinMaxClosestTo,
)

@Deprecated("Use io.github.gurgenky.charts.line.LineChartWithLegend")
@Composable
fun LineChartWithLegend(
    lineChartData: LineChartData,
    modifier: Modifier = Modifier,
    maxVerticalLines: Int = 10,
    maxHorizontalLines: Int = 10,
    animation: ChartAnimation = ChartAnimation.Simple(),
    colors: LineChartColors = ChartTheme.colors.lineChartColors,
    xAxisLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    yAxisLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    overlayHeaderLabel: @Composable (Any) -> Unit = { Text(it.toString()) },
    overlayDataEntryLabel: @Composable (String, Any) -> Unit = { name, value -> Text("$name: $value") },
    legendItemLabel: @Composable (String) -> Unit = { Text(it) },
) = io.github.gurgenky.charts.line.LineChartWithLegend(
    lineChartData = lineChartData,
    modifier = modifier,
    maxVerticalLines = maxVerticalLines,
    maxHorizontalLines = maxHorizontalLines,
    animation = animation.toCurrent(),
    colors = colors,
    xAxisLabel = xAxisLabel,
    yAxisLabel = yAxisLabel,
    overlayHeaderLabel = overlayHeaderLabel,
    overlayDataEntryLabel = overlayDataEntryLabel,
    legendItemLabel = legendItemLabel,
)
