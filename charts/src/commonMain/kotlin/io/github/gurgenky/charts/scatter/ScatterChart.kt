package io.github.gurgenky.charts.scatter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.gurgenky.charts.core.ChartConfig
import io.github.gurgenky.charts.core.ChartInteractionState
import io.github.gurgenky.charts.core.rememberChartInteractionState
import kotlin.math.abs

@Immutable
data class ScatterChartPoint(
    val x: Float,
    val y: Float,
    val radius: Dp = 5.dp,
)

@Immutable
data class ScatterChartSeries(
    val name: String,
    val points: List<ScatterChartPoint>,
    val color: Color,
)

@Immutable
data class ScatterChartData(val series: List<ScatterChartSeries>) {
    init {
        require(series.flatMap { it.points }.all { it.x.isFinite() && it.y.isFinite() }) {
            "Scatter chart coordinates must be finite."
        }
    }
}

/** Draws selectable Cartesian points and reports the nearest selected point. */
@Composable
fun ScatterChart(
    data: ScatterChartData,
    modifier: Modifier = Modifier,
    config: ChartConfig = ChartConfig(),
    state: ChartInteractionState = rememberChartInteractionState(),
    onSelectionChange: (seriesIndex: Int, pointIndex: Int) -> Unit = { _, _ -> },
) {
    val points = data.series.flatMapIndexed { seriesIndex, series ->
        series.points.mapIndexed { pointIndex, point -> IndexedPoint(seriesIndex, pointIndex, point) }
    }
    val minX = points.minOfOrNull { it.point.x } ?: 0f
    val maxX = points.maxOfOrNull { it.point.x } ?: 1f
    val minY = points.minOfOrNull { it.point.y } ?: 0f
    val maxY = points.maxOfOrNull { it.point.y } ?: 1f

    Canvas(
        modifier
            .semantics { contentDescription = "Scatter chart with ${points.size} points" }
            .pointerInput(data, config.interaction.enabled) {
                if (!config.interaction.enabled) return@pointerInput
                detectTapGestures { tap ->
                    val selected = points.minByOrNull {
                        val mapped = mapPoint(it.point, size.width.toFloat(), size.height.toFloat(), minX, maxX, minY, maxY)
                        abs(mapped.x - tap.x) + abs(mapped.y - tap.y)
                    }
                    selected?.let {
                        state.select(it.seriesIndex, it.pointIndex)
                        onSelectionChange(it.seriesIndex, it.pointIndex)
                    }
                }
            }
    ) {
        points.forEach {
            val mapped = mapPoint(it.point, size.width, size.height, minX, maxX, minY, maxY)
            val series = data.series[it.seriesIndex]
            val selected = state.selection?.let { selection ->
                selection.seriesIndex == it.seriesIndex && selection.itemIndex == it.pointIndex
            } == true
            drawCircle(
                color = series.color,
                radius = it.point.radius.toPx() * if (selected) 1.5f else 1f,
                center = mapped,
            )
        }
    }
}

private data class IndexedPoint(
    val seriesIndex: Int,
    val pointIndex: Int,
    val point: ScatterChartPoint,
)

private fun mapPoint(
    point: ScatterChartPoint,
    width: Float,
    height: Float,
    minX: Float,
    maxX: Float,
    minY: Float,
    maxY: Float,
): Offset = Offset(
    x = map(point.x, minX, maxX, 0f, width),
    y = map(point.y, minY, maxY, height, 0f),
)

private fun map(value: Float, min: Float, max: Float, outMin: Float, outMax: Float): Float {
    if (min == max) return (outMin + outMax) / 2f
    return (value - min) / (max - min) * (outMax - outMin) + outMin
}
