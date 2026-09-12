package io.github.gurgenky.charts.core

import androidx.compose.ui.graphics.Color
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ChartDslTest {
    @Test
    fun lineDslBuildsImmutableData() {
        val data = lineChartData {
            series("temperature", Color.Red) {
                point(1L, 12f)
                point(2L, 14f)
            }
        }

        assertEquals(2, data.series.single().listOfPoints.size)
        assertEquals(12f, data.minY)
        assertEquals(14f, data.maxY)
    }

    @Test
    fun lineDslRejectsNonFiniteValues() {
        assertFailsWith<IllegalArgumentException> {
            lineChartData { series("invalid", Color.Red) { point(1L, Float.NaN) } }
        }
    }
}
