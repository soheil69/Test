
import dependencies.AndroidTestDependencies
import dependencies.Dependencies
import dependencies.TestDependencies

plugins {
    id(Plugins.ANDROID_APPLICATION)
}

dependencies {
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(AndroidTestDependencies.EXT_JUNIT)
    androidTestImplementation(AndroidTestDependencies.ESPRESSO)
}
