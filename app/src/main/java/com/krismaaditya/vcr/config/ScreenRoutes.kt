package com.krismaaditya.vcr.config

sealed interface ScreenRoutes {

    @kotlinx.serialization.Serializable
    data object DashboardScreen: ScreenRoutes

    @kotlinx.serialization.Serializable
    data object CameraScreen: ScreenRoutes
}