package io.github.gurgenky.charts.radar

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.gurgenky.charts.theme.ChartTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

/** One polygon rendered against the axes of a radar chart. */
@Immutable
data class RadarChartSeries(
    val name: String,
    val values: List<Float>,
    val color: Color,
    val fillColor: Color = color.copy(alpha = 0.2f),
    val strokeWidth: Dp = 2.dp,
)

/** Validated axes, series, and scale range for a radar chart. */
@Immutable
data class RadarChartData(
    val axes: List<String>,
    val series: List<RadarChartSeries>,
    val minimum: Float = 0f,
    val maximum: Float = series.flatMap { it.values }.maxOrNull() ?: 1f,
) {
    init {
        require(axes.size >= 3) { "Radar charts require at least three axes." }
        require(minimum.isFinite() && maximum.isFinite() && maximum > minimum) {
            "Radar chart range must be finite and maximum must exceed minimum."
        }
        require(series.all { it.values.size == axes.size }) {
            "Every radar series must contain one value for each axis."
        }
        require(series.flatMap { it.values }.all(Float::isFinite)) {
            "Radar chart values must be finite."
        }
    }
}

/** Controls the radar web density, stroke sizes, and rotation. */
@Immutable
data class RadarChartConfig(
    val levels: Int = 5,
    val gridStrokeWidth: Dp = 1.dp,
    val axisStrokeWidth: Dp = 1.dp,
    val startAngleDegrees: Float = -90f,
) {
    init {
        require(levels > 0) { "Radar chart levels must be positive." }
    }
}

/**
 * Draws one or more data polygons on a shared polar web.
 *
 * Values outside [RadarChartData.minimum] and [RadarChartData.maximum] are clamped to the web.
 */
@Composable
fun RadarChart(
    data: RadarChartData,
    modifier: Modifier = Modifier,
    config: RadarChartConfig = RadarChartConfig(),
    gridColor: Color = ChartTheme.colors.grid,
    axisColor: Color = ChartTheme.colors.axis,
) {
    Canvas(modifier) {
        val radius = min(size.width, size.height) / 2f
        if (radius <= 0f) return@Canvas
        val vertices = vertices(data.axes.size, radius, config.startAngleDegrees)

        for (level in 1..config.levels) {
            val scale = level.toFloat() / config.levels
            drawPath(
                path = polygon(vertices.map { center + (it - center) * scale }),
                color = gridColor,
                style = Stroke(config.gridStrokeWidth.toPx()),
            )
        }

        vertices.forEach { vertex ->
            drawLine(axisColor, center, vertex, config.axisStrokeWidth.toPx())
        }

        data.series.forEach { series ->
            val points = vertices.mapIndexed { index, vertex ->
                val scale = ((series.values[index] - data.minimum) / (data.maximum - data.minimum))
                    .coerceIn(0f, 1f)
                center + (vertex - center) * scale
            }
            val path = polygon(points)
            drawPath(path, series.fillColor)
            drawPath(path, series.color, style = Stroke(series.strokeWidth.toPx()))
            points.forEach { drawCircle(series.color, radius = series.strokeWidth.toPx() * 1.5f, center = it) }
        }
    }
}

private fun DrawScope.vertices(count: Int, radius: Float, startAngle: Float): List<Offset> =
    List(count) { index ->
        val angle = (startAngle + index * 360f / count) * PI.toFloat() / 180f
        Offset(center.x + cos(angle) * radius, center.y + sin(angle) * radius)
    }

private fun polygon(points: List<Offset>) = Path().apply {
    if (points.isNotEmpty()) {
        moveTo(points.first().x, points.first().y)
        points.drop(1).forEach { lineTo(it.x, it.y) }
        close()
    }
}
