package io.github.gurgenky.charts.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.gurgenky.charts.area.AreaChart
import io.github.gurgenky.charts.area.AreaChartData
import io.github.gurgenky.charts.area.AreaChartPoint
import io.github.gurgenky.charts.area.AreaChartSeries
import io.github.gurgenky.charts.bar.BarChart
import io.github.gurgenky.charts.core.barChartData
import io.github.gurgenky.charts.core.lineChartData
import io.github.gurgenky.charts.line.LineChart
import io.github.gurgenky.charts.pie.PieChart
import io.github.gurgenky.charts.pie.PieChartData
import io.github.gurgenky.charts.radar.RadarChart
import io.github.gurgenky.charts.radar.RadarChartData
import io.github.gurgenky.charts.radar.RadarChartSeries
import io.github.gurgenky.charts.scatter.ScatterChart
import io.github.gurgenky.charts.scatter.ScatterChartData
import io.github.gurgenky.charts.scatter.ScatterChartPoint
import io.github.gurgenky.charts.scatter.ScatterChartSeries
import io.github.gurgenky.charts.theme.ChartTheme

private val Blue = Color(0xFF2563EB)
private val Cyan = Color(0xFF06B6D4)
private val Violet = Color(0xFF7C3AED)
private val Amber = Color(0xFFF59E0B)

@Composable
fun App() {
    ChartTheme {
        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Text("Compose Multiplatform Charts", style = MaterialTheme.typography.h4)
                Text("Interactive Canvas charts for Android, iOS, desktop, and Wasm.")

                ChartCard("Bar") {
                    BarChart(
                        data = barChartData {
                            category("Q1") { entry("Revenue", 32f, Blue); entry("Cost", 18f, Cyan) }
                            category("Q2") { entry("Revenue", 44f, Blue); entry("Cost", 23f, Cyan) }
                            category("Q3") { entry("Revenue", 51f, Blue); entry("Cost", 27f, Cyan) }
                        },
                        modifier = Modifier.fillMaxWidth().height(260.dp),
                    )
                }

                ChartCard("Line") {
                    LineChart(
                        lineChartData = lineChartData {
                            series("Downloads", Blue) {
                                point(0L, 8f); point(1L, 18f); point(2L, 13f); point(3L, 29f); point(4L, 38f)
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(260.dp),
                    )
                }

                ChartCard("Area") {
                    AreaChart(
                        AreaChartData(
                            listOf(AreaChartSeries("Active users", listOf(AreaChartPoint(0, 5f), AreaChartPoint(1, 16f), AreaChartPoint(2, 12f), AreaChartPoint(3, 28f)), Violet))
                        ),
                        modifier = Modifier.fillMaxWidth().height(260.dp),
                    )
                }

                ChartCard("Scatter") {
                    ScatterChart(
                        ScatterChartData(
                            listOf(ScatterChartSeries("Samples", listOf(ScatterChartPoint(1f, 4f), ScatterChartPoint(2f, 8f), ScatterChartPoint(3f, 6f), ScatterChartPoint(4f, 12f)), Cyan))
                        ),
                        modifier = Modifier.fillMaxWidth().height(260.dp),
                    )
                }

                ChartCard("Radar") {
                    RadarChart(
                        RadarChartData(
                            axes = listOf("Speed", "Quality", "Reach", "Value", "Support"),
                            series = listOf(RadarChartSeries("Product", listOf(82f, 74f, 91f, 68f, 86f), Blue)),
                            maximum = 100f,
                        ),
                        modifier = Modifier.size(300.dp),
                    )
                }

                ChartCard("Pie") {
                    PieChart(
                        listOf(
                            PieChartData("Android", 42.0, Blue),
                            PieChartData("iOS", 31.0, Violet),
                            PieChartData("Desktop", 17.0, Cyan),
                            PieChartData("Web", 10.0, Amber),
                        ),
                        modifier = Modifier.size(280.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun ChartCard(title: String, content: @Composable () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 2.dp) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(title, style = MaterialTheme.typography.h6)
            content()
        }
    }
}
