package io.github.gurgenky.charts.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.gurgenky.charts.bar.BarChartCategory
import io.github.gurgenky.charts.bar.BarChartData
import io.github.gurgenky.charts.bar.BarChartEntry
import io.github.gurgenky.charts.line.LineChartData
import io.github.gurgenky.charts.line.LineChartPoint
import io.github.gurgenky.charts.line.LineChartSeries

/** Restricts nested receiver lookup inside chart builder blocks. */
@DslMarker
annotation class ChartDslMarker

/** Controls one Cartesian axis. Null bounds are inferred from the supplied data. */
@Immutable
data class AxisConfig(
    val visible: Boolean = true,
    val minimum: Float? = null,
    val maximum: Float? = null,
    val maximumTickCount: Int = 5,
)

/** Controls plot grid visibility and line appearance. */
@Immutable
data class GridConfig(
    val visible: Boolean = true,
    val color: Color = Color.Unspecified,
    val strokeWidth: Dp = 1.dp,
    val dashPattern: List<Float> = emptyList(),
)

/** Enables selection and tooltip behavior for interactive charts. */
@Immutable
data class InteractionConfig(
    val enabled: Boolean = true,
    val showTooltip: Boolean = true,
    val selectOnHover: Boolean = true,
)

/** Controls whether and how the chart legend is laid out. */
@Immutable
data class LegendConfig(
    val visible: Boolean = false,
    val itemSpacing: Dp = 8.dp,
)

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
fun chartConfig(block: ChartConfigBuilder.() -> Unit): ChartConfig =
    ChartConfigBuilder().apply(block).build()

/** Builds one line series and validates each appended point. */
@ChartDslMarker
class LineSeriesBuilder internal constructor(
    private val name: String,
    private val color: Color,
) {
    var lineWidth: Dp = 3.dp
    var fillColor: Color = color
    var dashed: Boolean = false
    private val points = mutableListOf<LineChartPoint>()

    /** Adds a finite point to this series. */
    fun point(x: Long, y: Float) {
        require(y.isFinite()) { "Line chart values must be finite." }
        points += LineChartPoint(x, y)
    }

    internal fun build() = LineChartSeries(name, lineWidth, color, fillColor, dashed, points.toList())
}

/** Collects line-series builders into [LineChartData]. */
@ChartDslMarker
class LineChartDataBuilder {
    private val series = mutableListOf<LineChartSeries>()

    /** Adds a named line series. */
    fun series(name: String, color: Color, block: LineSeriesBuilder.() -> Unit) {
        series += LineSeriesBuilder(name, color).apply(block).build()
    }

    internal fun build() = LineChartData(series.toList())
}

/** Creates line-chart data with a type-safe Kotlin DSL. */
fun lineChartData(block: LineChartDataBuilder.() -> Unit): LineChartData =
    LineChartDataBuilder().apply(block).build()

/** Builds the entries in one bar-chart category. */
@ChartDslMarker
class BarCategoryBuilder internal constructor(private val name: String) {
    private val entries = mutableListOf<BarChartEntry>()

    /** Adds a finite value to the category. */
    fun entry(name: String, value: Float, color: Color) {
        require(value.isFinite()) { "Bar chart values must be finite." }
        entries += BarChartEntry(name, value, color)
    }

    internal fun build() = BarChartCategory(name, entries.toList())
}

/** Collects bar categories into [BarChartData]. */
@ChartDslMarker
class BarChartDataBuilder {
    private val categories = mutableListOf<BarChartCategory>()

    /** Adds a named category. */
    fun category(name: String, block: BarCategoryBuilder.() -> Unit) {
        categories += BarCategoryBuilder(name).apply(block).build()
    }

    internal fun build() = BarChartData(categories.toList())
}

/** Creates bar-chart data with a type-safe Kotlin DSL. */
fun barChartData(block: BarChartDataBuilder.() -> Unit): BarChartData =
    BarChartDataBuilder().apply(block).build()
