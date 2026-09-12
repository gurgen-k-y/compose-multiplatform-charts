---
name: upgrade-kmp
description: Upgrade Kotlin Multiplatform and Compose dependencies while checking all supported targets.
---

# Upgrade KMP

1. Confirm compatible Kotlin, Compose Multiplatform, AGP, Gradle, and Dokka versions from first-party release documentation.
2. Update the version catalog and build logic without adding publication plugins to gallery modules.
3. Migrate deprecated DSL or Compose APIs and preserve public chart behavior.
4. Compile Android, iOS simulator, desktop, and Wasm.
5. Record any host-specific validation gap precisely.
