package io.github.gurgenky.charts.line

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import io.github.gurgenky.charts.mapValueToDifferentRange

internal fun DrawScope.drawLineChart(
    lineChartData: LineChartData,
    graphTopPadding: Dp,
    graphBottomPadding: Dp,
    alpha: List<Float>,
    reusablePath: Path = Path(),
) {
    lineChartData.series.forEachIndexed { seriesIndex, data ->

        val mappedPoints =
            mapDataToPixels(
                lineChartData,
                data,
                size,
                graphTopPadding.toPx(),
                graphBottomPadding.toPx()
            )
        if (mappedPoints.isEmpty()) return@forEachIndexed
        val connectionPoints = calculateConnectionPointsForBezierCurve(mappedPoints)

        reusablePath.reset()
        mappedPoints.forEachIndexed { index, value ->
            if (index == 0) {
                reusablePath.moveTo(value.x, value.y)
            } else {
                reusablePath.cubicTo(
                    connectionPoints[index - 1].first.x,
                    connectionPoints[index - 1].first.y,
                    connectionPoints[index - 1].second.x,
                    connectionPoints[index - 1].second.y,
                    value.x,
                    value.y
                )
            }
        }

        // draw line
        clipRect {
            drawPath(
                path = reusablePath,
                color = data.lineColor.copy(alpha[seriesIndex]),
                style = Stroke(
                    width = data.lineWidth.toPx(),
                    pathEffect = if (data.dashedLine) dashedPathEffect else null
                )
            )

            reusablePath.lineTo(mappedPoints.last().x, size.height)
            reusablePath.lineTo(mappedPoints.first().x, size.height)
            reusablePath.close()
            drawPath(
                path = reusablePath,
                Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        data.fillColor.copy(alpha[seriesIndex] / 12),
                        data.fillColor.copy(alpha[seriesIndex] / 6)
                    ),
                    startY = reusablePath.getBounds().bottom,
                    endY = reusablePath.getBounds().top,
                ),
                style = Fill
            )
        }
    }
}

private fun mapDataToPixels(
    lineChartData: LineChartData,
    currentSeries: LineChartSeries,
    canvasSize: Size,
    graphTopPadding: Float = 0f,
    graphBottomPadding: Float,
): List<PointF> {
    val mappedPoints = currentSeries.listOfPoints.asSequence()
        .filter { it.x in lineChartData.minX..lineChartData.maxX }
        .map {
        val x = it.x.mapValueToDifferentRange(
            lineChartData.minX,
            lineChartData.maxX,
            0L,
            canvasSize.width.toLong()
        ).toFloat()
        val y = it.y.mapValueToDifferentRange(
            lineChartData.minY,
            lineChartData.maxY,
            canvasSize.height - graphBottomPadding,
            graphTopPadding
        )
        PointF(x, y)
        }
        .toList()

    return mappedPoints
}

private fun calculateConnectionPointsForBezierCurve(points: List<PointF>): MutableList<Pair<PointF, PointF>> {
    val conPoint = mutableListOf<Pair<PointF, PointF>>()
    for (i in 1 until points.size) {
        conPoint.add(
            Pair(
                PointF((points[i].x + points[i - 1].x) / 2f, points[i - 1].y),
                PointF((points[i].x + points[i - 1].x) / 2f, points[i].y)
            )
        )
    }
    return conPoint
}

data class PointF(val x: Float, val y: Float)
