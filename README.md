<div align="center">
  <img alt="Compose Multiplatform Charts" src="assets/charts-logo.svg" width="520">
  <h1>Compose Multiplatform Charts</h1>
  <p>Customizable, Compose-native charts for Android, iOS, desktop, and Wasm.</p>

  [![CI](https://github.com/gurgen-k-y/compose-multiplatform-charts/actions/workflows/ci.yml/badge.svg)](https://github.com/gurgen-k-y/compose-multiplatform-charts/actions/workflows/ci.yml)
  [![Maven Central](https://img.shields.io/maven-central/v/io.github.gurgen-k-y/compose-multiplatform-charts)](https://central.sonatype.com/artifact/io.github.gurgen-k-y/compose-multiplatform-charts)
  [![Pages](https://img.shields.io/badge/docs-GitHub_Pages-347cf6)](https://gurgen-k-y.github.io/compose-multiplatform-charts/)
  [![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE.md)
</div>

The library renders charts with Compose Canvas and exposes Kotlin-first data, configuration, theme, animation, and interaction APIs. It is distributed as one Maven artifact for every supported target.

This project is built on top of Netguru's original [`com.netguru.multiplatform` Compose Multiplatform Charts repository](https://github.com/netguru/compose-multiplatform-charts).

- [Live gallery](https://gurgen-k-y.github.io/compose-multiplatform-charts/demo/)
- [API documentation](https://gurgen-k-y.github.io/compose-multiplatform-charts/api/)

## Installation

Add Maven Central to dependency resolution and use the library from `commonMain`:

```kotlin
repositories {
    mavenCentral()
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.gurgen-k-y:compose-multiplatform-charts:1.0.0")
        }
    }
}
```

No GitHub credentials or platform-specific chart dependencies are required.

## Quick start

```kotlin
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.gurgenky.charts.ChartAnimation
import io.github.gurgenky.charts.core.lineChartData
import io.github.gurgenky.charts.line.LineChart

val data = lineChartData {
    series(name = "Revenue", color = Color(0xFF347CF6)) {
        lineWidth = 3.dp
        point(x = 1_725_148_800_000, y = 18f)
        point(x = 1_725_235_200_000, y = 27f)
        point(x = 1_725_321_600_000, y = 24f)
    }
}

LineChart(
    lineChartData = data,
    modifier = Modifier.height(280.dp),
    animation = ChartAnimation.Simple(),
)
```

The DSL rejects non-finite values immediately. Immutable constructors remain available when data already comes from a typed domain model.

## Included charts

| Chart | Highlights |
| --- | --- |
| Line | Multiple series, fills, dashed strokes, axes, overlays, legend |
| Area | Multiple non-stacked areas with independent fill and outline |
| Bar | Grouped positive/negative values, rounded bars, labels, legend |
| Scatter | Configurable points, selection state, selection callback |
| Pie / donut | Segment gaps, thickness, icon styles, horizontal/vertical legend |
| Radar | Multiple series, configurable web, vertices, fill, and semantics |
| Bubble | Packed proportional bubbles with custom content |
| Dial / percentage dial | Range, scale, arc joins, labels, animation |
| Gas bottle | Compact percentage visualization with interpolated fill color |

## Customize charts

Use `ChartTheme` to provide a consistent palette to every chart below it:

```kotlin
ChartTheme(
    colors = ChartDefaults.darkColors(
        primary = Color(0xFF8B5CF6),
        grid = Color(0xFF334155),
    ),
) {
    DashboardCharts()
}
```

Chart-specific configuration controls geometry such as bar thickness, pie gaps, radar web lines, axis tick density, and interaction behavior. Labels and legend content are composable slots, so typography and formatting stay in the consuming application.

For controllable selection, create and pass `rememberChartInteractionState()` and observe its `selection` value. Disable hit testing through `ChartConfig(interaction = InteractionConfig(enabled = false))` when a chart is display-only.

## Supported toolchain

| Component | Version |
| --- | --- |
| Kotlin | 2.4.20 |
| Compose Multiplatform | 1.12.0 |
| Android Gradle Plugin | 9.3.1 |
| Gradle | 9.5.0 |
| Android | minSdk 24, compileSdk 37 |
| Apple | iOS arm64 and iOS simulator arm64 |
| Desktop | JVM 17 |
| Web | Kotlin/Wasm browser |

## Migrating from the original package

Version 1.0 moves the public namespace from `com.netguru.multiplatform.charts` to `io.github.gurgenky.charts`. Deprecated forwarding APIs for the original bar, line, pie, bubble, dial, gas-bottle, grid, theme, and animation packages are included in the same artifact, so existing imports can be migrated incrementally.

```diff
- import com.netguru.multiplatform.charts.line.LineChart
+ import io.github.gurgenky.charts.line.LineChart
```

New charts, the DSL, interaction state, and expanded theming are available only under the new namespace.

## Gallery and documentation

Run the desktop gallery:

```shell
./gradlew :example:run
```

Run the Wasm gallery with continuous rebuild:

```shell
./gradlew :example:wasmJsBrowserDevelopmentRun --continuous
```

Generate API docs locally:

```shell
./gradlew :charts:dokkaGenerate
```

GitHub Pages publishes the landing page, live Wasm gallery, and Dokka API from `main`.

## Build and contribute

```shell
./gradlew \
  :charts:desktopTest \
  :charts:compileKotlinIosSimulatorArm64 \
  :example:wasmJsBrowserDistribution \
  :androidApp:assembleDebug \
  :charts:publishToMavenLocal
```

See [CONTRIBUTING.md](CONTRIBUTING.md), [SECURITY.md](SECURITY.md), and [CLAUDE.md](CLAUDE.md) for project conventions. Releases use a protected GitHub environment and publish only the `charts` module as `io.github.gurgen-k-y:compose-multiplatform-charts`.

Release notes live in GitHub Releases and commit history; this repository intentionally has no changelog file.

## License

[MIT](LICENSE.md) © 2022 Netguru and contributors.
