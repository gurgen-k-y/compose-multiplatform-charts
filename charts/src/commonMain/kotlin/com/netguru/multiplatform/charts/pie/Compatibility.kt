@file:Suppress("DEPRECATION")

package com.netguru.multiplatform.charts.pie

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netguru.multiplatform.charts.ChartAnimation
import com.netguru.multiplatform.charts.toCurrent

@Deprecated("Use io.github.gurgenky.charts.pie.PieChartData")
typealias PieChartData = io.github.gurgenky.charts.pie.PieChartData

@Deprecated("Use io.github.gurgenky.charts.pie.PieChartConfig")
typealias PieChartConfig = io.github.gurgenky.charts.pie.PieChartConfig

@Deprecated("Use io.github.gurgenky.charts.pie.LegendIcon")
typealias LegendIcon = io.github.gurgenky.charts.pie.LegendIcon

@Deprecated("Use io.github.gurgenky.charts.pie.LegendOrientation")
typealias LegendOrientation = io.github.gurgenky.charts.pie.LegendOrientation

@Deprecated("Use io.github.gurgenky.charts.pie.PieChart")
@Composable
fun PieChart(
    data: List<PieChartData>,
    modifier: Modifier = Modifier,
    animation: ChartAnimation = ChartAnimation.Simple(),
    config: PieChartConfig = PieChartConfig(),
) = io.github.gurgenky.charts.pie.PieChart(data, modifier, animation.toCurrent(), config)

@Deprecated("Use io.github.gurgenky.charts.pie.PieChartWithLegend")
@Composable
fun PieChartWithLegend(
    pieChartData: List<PieChartData>,
    modifier: Modifier = Modifier,
    animation: ChartAnimation = ChartAnimation.Simple(),
    config: PieChartConfig = PieChartConfig(),
    legendItemLabel: @Composable (PieChartData) -> Unit = { Text(it.name) },
) = io.github.gurgenky.charts.pie.PieChartWithLegend(
    pieChartData = pieChartData,
    modifier = modifier,
    animation = animation.toCurrent(),
    config = config,
    legendItemLabel = legendItemLabel,
)
