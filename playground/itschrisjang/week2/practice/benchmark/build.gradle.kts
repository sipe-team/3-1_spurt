allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

dependencies {
    jmh("org.openjdk.jmh:jmh-core:${property("jmhVersion")}")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:${property("jmhVersion")}")
}