---
name: add-chart
description: Add a Compose Multiplatform chart or substantial variant with API, rendering, tests, docs, and gallery coverage.
---

# Add a chart

1. Read `CLAUDE.md` and the chart API and Canvas rules.
2. Define immutable data, configuration, colors, interaction state, and validation in `commonMain`.
3. Keep geometry calculations testable outside `DrawScope`; cache reusable rendering objects.
4. Add portable tests for edge cases and a realistic example to the gallery.
5. Add the chart to the README matrix and link its API documentation.
6. Run `./gradlew :charts:desktopTest :example:compileKotlinDesktop :example:wasmJsBrowserDistribution`.
