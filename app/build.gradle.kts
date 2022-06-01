plugins {
    id("local.java-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":list"))
}

application {
    mainClass.set("ca.irreducible.tmpl.app.App")
}
