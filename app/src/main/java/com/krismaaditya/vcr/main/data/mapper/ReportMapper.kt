package com.krismaaditya.vcr.main.data.mapper

import com.krismaaditya.vcr.main.data.data_sources.remote.ReportDto
import com.krismaaditya.vcr.main.domain.entity.ReportEntity

fun ReportDto.toReportEntity() : ReportEntity = ReportEntity(
    createdAt = createdAt,
    createdBy = createdBy,
    note = note,
    photo = photo,
    reportId = reportId,
    reportStatus = reportStatus,
    vehicleId = vehicleId,
    vehicleLicenseNumber = vehicleLicenseNumber,
    vehicleName = vehicleName
)