package com.krismaaditya.vcr.main.domain.entity

data class ReportEntity(
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