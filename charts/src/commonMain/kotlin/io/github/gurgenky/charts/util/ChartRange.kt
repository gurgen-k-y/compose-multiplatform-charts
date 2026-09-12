package io.github.gurgenky.charts.util

/** A finite numeric range shared by Cartesian chart implementations. */
data class ChartRange(val minimum: Float, val maximum: Float) {
    init {
        require(minimum.isFinite() && maximum.isFinite() && maximum >= minimum) {
            "Chart ranges must be finite and ordered."
        }
    }

    fun normalize(value: Float): Float =
        if (minimum == maximum) 0.5f else ((value - minimum) / (maximum - minimum)).coerceIn(0f, 1f)
}
