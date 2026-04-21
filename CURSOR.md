# Cursor Project Guide

This repository contains a Compose Multiplatform chart library and a cross-platform example app.

## Rendering

- Calculate reusable geometry before drawing and cache paths when their inputs are stable.
- Treat density, canvas size, data, and configuration as cache keys.
- Clip rendering to the plot viewport and avoid work for points outside the visible range.
- Keep drawing code side-effect free; do not update Compose state from a drawing callback.

## State

- Prefer immutable public models annotated with `@Immutable`.
- Hoist selection and viewport state when callers need to observe or control it.
- Key `remember` and pointer-input blocks with every value they capture.

## API changes

- Preserve source compatibility when practical and deprecate replaced APIs with migration guidance.
- Document every public declaration and demonstrate new behavior in the example application.
