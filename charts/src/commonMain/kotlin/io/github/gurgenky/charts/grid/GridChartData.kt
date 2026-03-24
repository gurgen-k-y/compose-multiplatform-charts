package io.github.gurgenky.charts.grid

import io.github.gurgenky.charts.line.LegendItemData

interface GridChartData {
    val minX: Long
    val maxX: Long
    val minY: Float
    val maxY: Float
    val legendData: List<LegendItemData>
}
