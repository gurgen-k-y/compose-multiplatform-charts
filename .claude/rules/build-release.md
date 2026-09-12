---
paths:
  - "**/*.gradle.kts"
  - "gradle/**"
  - ".github/workflows/**"
---

# Build and release

- `:charts` is the only publishable module and produces the single Maven coordinate.
- Keep Kotlin, Compose Multiplatform, AGP, Gradle, and Dokka versions mutually compatible.
- Never place credentials in tracked files. Maven Central credentials and in-memory signing keys come from CI secrets.
- A release must compile Android, iOS, desktop, and Wasm, generate docs, and publish successfully to Maven Local first.
- Never push, tag, publish, or create a GitHub release unless the user explicitly requests that external action.
