# Lecture 02: Creating Library Modules

## Creating a Module
- Right-click Project -> New -> Module
- Choose "Android Library" or "Kotlin/Java Library"
- Package naming conventions (`com.example.project.module`)

## Build Files
- The `build.gradle.kts` for a library
- Difference between `com.android.application` and `com.android.library`

## Using the Module
- Adding `include(":module-name")` to `settings.gradle.kts`
- Adding `implementation(project(":module-name"))` to `app/build.gradle.kts`
