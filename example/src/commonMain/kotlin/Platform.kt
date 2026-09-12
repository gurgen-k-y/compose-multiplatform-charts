package io.github.gurgenky.charts.example

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform