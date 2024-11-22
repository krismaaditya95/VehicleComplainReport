package com.krismaaditya.vcr.main.presentation.dashboard

sealed interface DashboardScreenAction {
    data object LoadAllReports: DashboardScreenAction

    data object LoadAllVehicles: DashboardScreenAction

    data class OnAddReport(val newDate: Int): DashboardScreenAction

    data object ShowAddReportBottomSheet: DashboardScreenAction

    data object HideAddReportBottomSheet: DashboardScreenAction

    data class OnReportNoteValueChanged(
        val note: String
    ): DashboardScreenAction

    data object OnFormSaved: DashboardScreenAction
}
