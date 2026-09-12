package io.github.gurgenky.charts.area

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.gurgenky.charts.ChartAnimation
import io.github.gurgenky.charts.core.ChartConfig
import io.github.gurgenky.charts.line.LineChart
import io.github.gurgenky.charts.line.LineChartData
import io.github.gurgenky.charts.line.LineChartPoint
import io.github.gurgenky.charts.line.LineChartSeries

typealias AreaChartPoint = LineChartPoint

/** A non-stacked area series with independently configurable fill and outline. */
@Immutable
data class AreaChartSeries(
    val name: String,
    val points: List<AreaChartPoint>,
    val color: Color,
    val fillColor: Color = color.copy(alpha = 0.25f),
    val lineWidth: Dp = 2.dp,
)

/** Data rendered by [AreaChart]. */
@Immutable
data class AreaChartData(val series: List<AreaChartSeries>)

/** Draws one or more non-stacked areas on a shared Cartesian plot. */
@Composable
fun AreaChart(
    data: AreaChartData,
    modifier: Modifier = Modifier,
    config: ChartConfig = ChartConfig(),
    animation: ChartAnimation = ChartAnimation.Simple(),
) {
    LineChart(
        lineChartData = LineChartData(
            data.series.map {
                LineChartSeries(
                    dataName = it.name,
                    lineWidth = it.lineWidth,
                    lineColor = it.color,
                    fillColor = it.fillColor,
                    listOfPoints = it.points,
                )
            }
        ),
        modifier = modifier,
        animation = animation,
        maxVerticalLines = config.xAxis.maximumTickCount,
        maxHorizontalLines = config.yAxis.maximumTickCount,
    )
}
