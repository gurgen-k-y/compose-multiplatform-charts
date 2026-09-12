---
paths:
  - "charts/src/commonMain/**/*.kt"
---

# Public chart API

- Keep public data immutable and KDoc every public declaration.
- Validate empty data, non-finite values, invalid ranges, and mismatched dimensions at the boundary.
- Prefer additive options with stable defaults. Preserve old source paths with deprecated forwarding APIs.
- Put shared behavior in `commonMain`; platform implementations require a documented Compose limitation.
- Add focused portable tests for calculations and a gallery example for visible behavior.
