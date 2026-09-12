---
paths:
  - "charts/src/commonMain/kotlin/**/charts/**/*.kt"
---

# Canvas rendering

- Cache collections, paths, brushes, and formatters that would otherwise be allocated per frame.
- Clip drawing to the plot area and cull data that cannot affect visible output.
- Never mutate Compose state from `DrawScope`; calculate layout before drawing or expose immutable results.
- Keep geometry deterministic and express rendering constants with named values.
- Hoist interaction state when callers need to observe or control selection.
