@file:Suppress("DEPRECATION")

package com.netguru.multiplatform.charts.bubble

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.netguru.multiplatform.charts.ChartAnimation
import com.netguru.multiplatform.charts.toCurrent

@Deprecated("Use io.github.gurgenky.charts.bubble.Bubble")
typealias Bubble = io.github.gurgenky.charts.bubble.Bubble

@Deprecated("Use io.github.gurgenky.charts.bubble.BubbleChart")
@Composable
fun BubbleChart(
    bubbles: List<Bubble>,
    modifier: Modifier = Modifier,
    animation: ChartAnimation = ChartAnimation.Simple(),
    distanceBetweenCircles: Float = -10f,
    bubbleLabel: @Composable (Bubble) -> Unit = { Text(it.name) },
) = io.github.gurgenky.charts.bubble.BubbleChart(
    bubbles = bubbles,
    modifier = modifier,
    animation = animation.toCurrent(),
    distanceBetweenCircles = distanceBetweenCircles,
    bubbleLabel = bubbleLabel,
)
