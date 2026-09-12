---
name: validate-charts
description: Run the repository's cross-platform chart validation before review or release.
---

# Validate charts

Run:

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

Then run `git diff --check` and inspect the Maven Local POM/module metadata. Do not publish remotely.
