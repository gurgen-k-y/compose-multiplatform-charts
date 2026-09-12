---
name: prepare-release
description: Prepare and audit a single-artifact Maven Central release without performing external release actions.
disable-model-invocation: true
---

# Prepare a release

1. Require an explicit release version and verify the tree is clean.
2. Run the `validate-charts` workflow.
3. Confirm `:charts` is the only module with Maven publications and the generated coordinate is `io.github.gurgen-k-y:compose-multiplatform-charts`.
4. Verify POM license, developer, SCM, issue, sources, docs, and Gradle module metadata.
5. Verify the release workflow consumes only environment secrets and is limited to `v*` tags.
6. Report the exact tag that would be created and stop. Tagging, pushing, Central publication, and GitHub release creation need explicit authorization.
