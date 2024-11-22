package com.krismaaditya.vcr.main.presentation.dashboard

import com.krismaaditya.vcr.main.domain.entity.ReportEntity
import com.krismaaditya.vcr.main.domain.entity.VehicleEntity
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class DashboardScreenState(
    val vehicleList: List<VehicleEntity> = emptyList(),
    val reportList: List<ReportEntity> = emptyList(),
    val currentUser: String = "Albertus Krisma Aditya Giovanni",
    val isAddReportBottomSheetVisible: Boolean = false,
    val isAllReportLoading: Boolean = false,
    val isError: Boolean = false,
    // FORM
    val isAllVehicleLoading: Boolean = false,
    val currentDate: String = DateTimeFormatter.ofPattern("dd-MMMM-yyyy").format(ZonedDateTime.now()),
    val selectedVehicle: String = "Pilih Kendaraan",
    val note: String = "",
    val imageUri: String = "https://static.thenounproject.com/png/4619810-200.png"
)
