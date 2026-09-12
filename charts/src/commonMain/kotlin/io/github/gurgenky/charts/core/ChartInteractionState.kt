package io.github.gurgenky.charts.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/** Identifies a selected item without coupling shared interaction state to one chart model. */
@Stable
data class ChartSelection(
    val seriesIndex: Int,
    val itemIndex: Int,
)

/** Hoistable selection state shared by interactive chart implementations. */
@Stable
class ChartInteractionState(initialSelection: ChartSelection? = null) {
    var selection by mutableStateOf(initialSelection)
        private set

    fun select(seriesIndex: Int, itemIndex: Int) {
        selection = ChartSelection(seriesIndex, itemIndex)
    }

    fun clearSelection() {
        selection = null
    }
}

/** Remembers a [ChartInteractionState] for the current composition. */
@Composable
fun rememberChartInteractionState(
    initialSelection: ChartSelection? = null,
): ChartInteractionState = remember { ChartInteractionState(initialSelection) }
