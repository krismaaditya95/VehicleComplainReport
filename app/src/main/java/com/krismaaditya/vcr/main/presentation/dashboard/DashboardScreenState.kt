package com.krismaaditya.vcr.main.presentation.dashboard

import com.krismaaditya.vcr.main.domain.entity.ReportEntity
import com.krismaaditya.vcr.main.domain.entity.VehicleEntity
import java.time.ZonedDateTime

data class DashboardScreenState(
    val vehicleList: List<VehicleEntity> = emptyList(),
    val reportList: List<ReportEntity> = emptyList(),
    val currentUser: String = "Albertus Krisma Aditya Giovanni",
    val currentDate: ZonedDateTime = ZonedDateTime.now(),
    val isAddReportBottomSheetVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
