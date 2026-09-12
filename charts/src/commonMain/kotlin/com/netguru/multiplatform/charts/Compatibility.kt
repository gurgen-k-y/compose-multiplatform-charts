@file:Suppress("DEPRECATION")

package com.netguru.multiplatform.charts

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween

@Deprecated(
    message = "Use io.github.gurgenky.charts.ChartAnimation",
    replaceWith = ReplaceWith("ChartAnimation", "io.github.gurgenky.charts.ChartAnimation"),
)
sealed class ChartAnimation {
    data object Disabled : ChartAnimation()

    class Simple(
        val animationSpec: () -> AnimationSpec<Float> = { tween(300, 100) },
    ) : ChartAnimation()

    class Sequenced(
        val animationSpec: (Int) -> AnimationSpec<Float> = { index -> tween(300, index * 100) },
    ) : ChartAnimation()
}

internal fun ChartAnimation.toCurrent(): io.github.gurgenky.charts.ChartAnimation = when (this) {
    ChartAnimation.Disabled -> io.github.gurgenky.charts.ChartAnimation.Disabled
    is ChartAnimation.Simple -> io.github.gurgenky.charts.ChartAnimation.Simple(animationSpec)
    is ChartAnimation.Sequenced -> io.github.gurgenky.charts.ChartAnimation.Sequenced(animationSpec)
}
