rootProject.name = "tmpl"

val excludedDirs = setOf("buildSrc", "gradle")
val buildFiles = setOf("build.gradle.kts", "build.gradle")


settingsDir.listFiles()?.forEach { subDir: File ->
    val isBuildDir = !subDir.listFiles { _, name ->
        buildFiles.contains(name)
    }.isNullOrEmpty()

    if (isBuildDir) {
        val moduleName = subDir.name

        if (!excludedDirs.contains(moduleName)) {
            settings.include(subDir.name)
        }
    }
}

