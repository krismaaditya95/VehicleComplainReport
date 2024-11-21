package com.krismaaditya.vcr.main.data.data_sources.remote

import kotlinx.serialization.Serializable

@Serializable
data class VehicleDto(
    val licenseNumber: String,
    val type: String,
    val vehicleId: String
)