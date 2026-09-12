package io.github.gurgenky.charts.radar

import androidx.compose.ui.graphics.Color
import kotlin.test.Test
import kotlin.test.assertFailsWith

class RadarChartDataTest {
    @Test
    fun requiresOneValuePerAxis() {
        assertFailsWith<IllegalArgumentException> {
            RadarChartData(
                axes = listOf("A", "B", "C"),
                series = listOf(RadarChartSeries("invalid", listOf(1f, 2f), Color.Blue)),
            )
        }
    }
}
