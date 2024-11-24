package com.krismaaditya.vcr.main.data.data_sources.remote

import android.media.Image

data class AddReportDto(
    val vehicleId: String,
    val note: String,
    val userId: String,
    val photo: Image
)
