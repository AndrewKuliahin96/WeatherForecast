plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
}

allprojects {
    val kotlinLint by configurations.creating

    dependencies {
        kotlinLint("com.pinterest.ktlint:ktlint-cli:1.2.1") {
            attributes {
                attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
            }
        }
    }

    tasks.register<JavaExec>("ktlint") {
        description = "Check Kotlin code style."
        mainClass.set("com.pinterest.ktlint.Main")
        classpath = kotlinLint
        args("src/**/*.kt")
        jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
    }

    tasks.register<JavaExec>("ktlintFormat") {
        group = "formatting"
        description = "Fix Kotlin code style deviations."
        mainClass.set("com.pinterest.ktlint.Main")
        classpath = kotlinLint
        args("-F", "src/**/*.kt")
        jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
    }
}
