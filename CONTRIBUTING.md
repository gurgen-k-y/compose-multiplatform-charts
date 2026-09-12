# Contributing

Thanks for improving Compose Multiplatform Charts.

## Before opening a pull request

1. Discuss large API changes in an issue first.
2. Keep one pull request focused on one feature or defect.
3. Add portable tests for behavior and a gallery example for visible changes.
4. Document every new public declaration and preserve source compatibility where practical.
5. Run the validation commands below.

## Project structure

- `charts` is the shared library and the only published module.
- `example` contains shared gallery UI plus desktop and Wasm launchers.
- `androidApp` packages the Android gallery.
- `site` contains the GitHub Pages landing page; the live gallery and API reference are generated in CI.

The public namespace is `io.github.gurgenky.charts`. Deprecated `com.netguru.multiplatform.charts` forwarders are retained for migration and should not receive new APIs.

## Validation

Use JDK 17 and run:

```shell
./gradlew \
  :charts:desktopTest \
  :charts:compileKotlinIosSimulatorArm64 \
  :example:compileKotlinDesktop \
  :example:wasmJsBrowserDistribution \
  :androidApp:assembleDebug \
  :charts:dokkaGenerate \
  :charts:publishToMavenLocal
```

The iOS task requires macOS. Linux contributors can rely on the macOS CI job for that target while running all other applicable checks locally.

## Documentation

Generate Dokka locally with:

```shell
./gradlew :charts:dokkaGenerate
```

Open `charts/build/dokka/html/index.html`. Do not commit generated documentation or add a changelog file; Pages regenerates API docs, and release notes belong in GitHub Releases.

## Pull requests

Explain the motivation, user-visible behavior, compatibility impact, and validation performed. Screenshots or short recordings are encouraged for rendering changes. Link the relevant issue when one exists.
