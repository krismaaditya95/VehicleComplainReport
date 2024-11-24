package com.krismaaditya.vcr.main.presentation.dashboard

import android.graphics.Bitmap
import android.media.Image
import com.krismaaditya.vcr.main.domain.entity.ReportEntity
import com.krismaaditya.vcr.main.domain.entity.VehicleEntity
import java.io.File
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
    val selectedVehicle: VehicleEntity = VehicleEntity(
        licenseNumber = "",
        type = "Pilih Kendaraan",
        vehicleId = ""
    ),
    val note: String = "",
    val imageUri: String = "",
    val isCaptureLoading: Boolean = false,
    val isCaptureError: Boolean = false,
    val rawBitmap: Bitmap? = null,
    val isFormSubmitLoading: Boolean = false,
    val isFormSubmitError: Boolean = false,
    val formSubmitResponse: String = ""
)
