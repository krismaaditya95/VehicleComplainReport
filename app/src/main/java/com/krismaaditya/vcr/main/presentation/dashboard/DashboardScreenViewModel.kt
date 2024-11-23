package com.krismaaditya.vcr.main.presentation.dashboard

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krismaaditya.vcr.main.domain.repository.ReportListResult
import com.krismaaditya.vcr.main.domain.repository.VehicleListResult
import com.krismaaditya.vcr.main.domain.usecase.GetAllReportUseCase
import com.krismaaditya.vcr.main.domain.usecase.GetAllVehicleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardScreenViewModel(
    private val appContext: Context,
    private val getAllVehicleUseCase: GetAllVehicleUseCase,
    private val getAllReportUseCase: GetAllReportUseCase
) : ViewModel(){

    var state by mutableStateOf(DashboardScreenState())
        private set

    fun onAction(action: DashboardScreenAction){
        when (action){
            DashboardScreenAction.LoadAllReports -> loadAllReports()
            DashboardScreenAction.LoadAllVehicles -> loadAllVehicles()
            is DashboardScreenAction.OnAddReport -> TODO()
            DashboardScreenAction.ShowAddReportBottomSheet -> showAddReportBottomSheet()
            DashboardScreenAction.HideAddReportBottomSheet -> hideAddReportBottomSheet()
            DashboardScreenAction.OnFormSaved -> TODO()
            is DashboardScreenAction.OnReportNoteValueChanged -> {
                state = state.copy(
                    note = action.note
                )
            }

            DashboardScreenAction.StartCameraService -> TODO()
        }
    }

    companion object {
        val CAMERA_PERMISSION = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    }

    fun arePermissionGranted(activity: Activity): Boolean{
        return CAMERA_PERMISSION.all { permission ->
            ContextCompat.checkSelfPermission(
                activity.applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun loadAllVehicles(){
        viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(
                isAllVehicleLoading = true
            )

            getAllVehicleUseCase().collect{ result ->
                state = when(result){
                    is VehicleListResult.Error -> {
                        state.copy(
                            isError = true
                        )
                    }

                    is VehicleListResult.Success -> {
                        state.copy(
                            vehicleList = result.data ?: emptyList()
                        )
                    }
                }
            }

            state = state.copy(
                isAllVehicleLoading = false
            )
        }
    }

    private fun loadAllReports(){
        viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(
                isAllReportLoading = true
            )

            getAllReportUseCase().collect{ result ->
                when(result){
                    is ReportListResult.Error -> {
                        state = state.copy(
                            isError = true
                        )
                    }
                    is ReportListResult.Success -> {
                        state = state.copy(
                            reportList = result.data ?: emptyList()
                        )
                    }
                }
            }

            state = state.copy(
                isAllReportLoading = false
            )
        }
    }

    private fun showAddReportBottomSheet(){
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(
                isAllVehicleLoading = true,
                isAddReportBottomSheetVisible = true
            )

            getAllVehicleUseCase().collect{ result ->
                state = when(result){
                    is VehicleListResult.Error -> {
                        state.copy(
                            isError = true
                        )
                    }

                    is VehicleListResult.Success -> {
                        state.copy(
                            vehicleList = result.data ?: emptyList()
                        )
                    }
                }
            }

            state = state.copy(
                isAllVehicleLoading = false,
            )
        }
    }

    private fun hideAddReportBottomSheet(){
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(
                isAddReportBottomSheetVisible = false
            )
        }
    }
}