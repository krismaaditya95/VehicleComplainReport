package com.krismaaditya.vcr.main.presentation.dashboard

sealed interface DashboardScreenAction {
    data object LoadAllReports: DashboardScreenAction

    data object LoadAllVehicles: DashboardScreenAction

    data class OnAddReport(val newDate: Int): DashboardScreenAction
}
