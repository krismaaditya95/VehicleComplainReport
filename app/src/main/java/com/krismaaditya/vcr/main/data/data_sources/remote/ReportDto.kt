package com.krismaaditya.vcr.main.data.data_sources.remote

import kotlinx.serialization.Serializable

@Serializable
data class ReportDto(
    val createdAt: String,
    val createdBy: String,
    val note: String,
    val photo: String,
    val reportId: String,
    val reportStatus: String,
    val vehicleId: String,
    val vehicleLicenseNumber: String,
    val vehicleName: String
)