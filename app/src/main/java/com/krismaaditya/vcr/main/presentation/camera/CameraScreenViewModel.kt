package com.krismaaditya.vcr.main.presentation.camera

import android.app.Activity
import androidx.camera.core.ImageCapture
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krismaaditya.vcr.main.domain.usecase.CameraUseCase
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CameraScreenViewModel(
    private val cameraUseCase: CameraUseCase,
    private val dashboardScreenViewModel: DashboardScreenViewModel
): ViewModel() {

    fun onAction(action: CameraScreenAction){
        when(action){
            is CameraScreenAction.TakePicture -> takePicture(
                imageCapture = action.imageCapture,
                activity = action.activity
            )
        }
    }


    private fun takePicture(
        imageCapture: ImageCapture?,
        activity: Activity
    ){
        viewModelScope.launch(Dispatchers.IO) {
//            dashboardScreenViewModel.state =
            cameraUseCase.invoke(imageCapture, activity)
        }
    }
}