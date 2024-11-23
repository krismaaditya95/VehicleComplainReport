package com.krismaaditya.vcr.main.presentation.dashboard

import android.app.Activity
import androidx.camera.core.ImageCapture
import com.krismaaditya.vcr.main.presentation.camera.CameraScreenAction

sealed interface DashboardScreenAction {
    data object LoadAllReports: DashboardScreenAction

    data object LoadAllVehicles: DashboardScreenAction

    data class OnAddReport(val newDate: Int): DashboardScreenAction

    data object ShowAddReportBottomSheet: DashboardScreenAction

    data object HideAddReportBottomSheet: DashboardScreenAction

    data object StartCameraService: DashboardScreenAction

    data class OnReportNoteValueChanged(
        val note: String
    ): DashboardScreenAction

    data class TakePicture(
        val imageCapture: ImageCapture?,
        val activity: Activity,
        val callback: () -> Unit
    ): DashboardScreenAction

    data object OnFormSaved: DashboardScreenAction
}
