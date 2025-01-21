import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.netguru.multiplatform.charts.ChartAnimation
import com.netguru.multiplatform.charts.pie.LegendIcon
import com.netguru.multiplatform.charts.pie.PieChartConfig
import com.netguru.multiplatform.charts.pie.PieChartData
import com.netguru.multiplatform.charts.pie.PieChartWithLegend
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Pie Chart Example")
                    }
                )
            }
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PieChartWithLegend(
                    modifier = Modifier.size(300.dp),
                    animation = ChartAnimation.Simple(),
                    legendItemLabel = { data ->
                        Text(data.name)
                    },
                    config = PieChartConfig(
                        legendIcon = LegendIcon.ROUND
                    ),
                    pieChartData = listOf(
                        PieChartData(
                            name = "Lupa",
                            value = 20.0,
                            color = Color.Cyan
                        ),
                        PieChartData(
                            name = "Pupa",
                            value = 10.0,
                            color = Color.Red
                        ),
                        PieChartData(
                            name = "Zalupa",
                            value = 15.0,
                            color = Color.Yellow
                        )
                    )
                )
            }
        }
    }
}