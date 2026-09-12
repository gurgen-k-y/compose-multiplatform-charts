package io.github.gurgenky.charts.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Restricts nested receiver lookup inside chart builder blocks. */
@DslMarker
annotation class ChartDslMarker

/** Controls one Cartesian axis. Null bounds are inferred from the supplied data. */
@Immutable
data class AxisConfig(val visible: Boolean = true, val minimum: Float? = null, val maximum: Float? = null, val maximumTickCount: Int = 5)

/** Controls plot grid visibility and line appearance. */
@Immutable
data class GridConfig(val visible: Boolean = true, val color: Color = Color.Unspecified, val strokeWidth: Dp = 1.dp, val dashPattern: List<Float> = emptyList())

/** Enables selection and tooltip behavior for interactive charts. */
@Immutable
data class InteractionConfig(val enabled: Boolean = true, val showTooltip: Boolean = true, val selectOnHover: Boolean = true)

/** Controls whether and how the chart legend is laid out. */
@Immutable
data class LegendConfig(val visible: Boolean = false, val itemSpacing: Dp = 8.dp)

/** Common configuration shared by Cartesian chart implementations. */
@Immutable
data class ChartConfig(
    val xAxis: AxisConfig = AxisConfig(),
    val yAxis: AxisConfig = AxisConfig(),
    val grid: GridConfig = GridConfig(),
    val interaction: InteractionConfig = InteractionConfig(),
    val legend: LegendConfig = LegendConfig(),
    val contentPadding: Dp = 8.dp,
)

/** Builds immutable common chart configuration. */
@ChartDslMarker
class ChartConfigBuilder {
    var xAxis: AxisConfig = AxisConfig()
    var yAxis: AxisConfig = AxisConfig()
    var grid: GridConfig = GridConfig()
    var interaction: InteractionConfig = InteractionConfig()
    var legend: LegendConfig = LegendConfig()
    var contentPadding: Dp = 8.dp

    fun build() = ChartConfig(xAxis, yAxis, grid, interaction, legend, contentPadding)
}

/** Creates common chart configuration with a type-safe Kotlin DSL. */
fun chartConfig(block: ChartConfigBuilder.() -> Unit): ChartConfig = ChartConfigBuilder().apply(block).build()
