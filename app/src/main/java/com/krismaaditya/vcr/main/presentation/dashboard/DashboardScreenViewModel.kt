package com.krismaaditya.vcr.main.presentation.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krismaaditya.vcr.main.domain.repository.ReportListResult
import com.krismaaditya.vcr.main.domain.repository.VehicleListResult
import com.krismaaditya.vcr.main.domain.usecase.GetAllReportUseCase
import com.krismaaditya.vcr.main.domain.usecase.GetAllVehicleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class DashboardScreenViewModel(
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
        }
    }

    private fun loadAllVehicles(){
        viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(
                isLoading = true
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
                isLoading = false
            )
        }
    }

    private fun loadAllReports(){
        viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(
                isLoading = true
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
                isLoading = false
            )
        }
    }
}