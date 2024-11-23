package com.krismaaditya.vcr.main.presentation.camera

import android.app.Activity
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krismaaditya.vcr.main.domain.usecase.CameraUseCase
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CameraScreenViewModel(
    private val cameraUseCase: CameraUseCase
): ViewModel() {

    var state by mutableStateOf(CameraScreenState())
        private set

    fun onAction(action: CameraScreenAction){
        when(action){
            is CameraScreenAction.TakePicture -> takePicture(
                imageCapture = action.imageCapture,
                activity = action.activity,
                callback = action.callback
            )
        }
    }


    private fun takePicture(
        imageCapture: ImageCapture?,
        activity: Activity,
        callback: () -> Unit,
    ){
        viewModelScope.launch(Dispatchers.IO) {
//            dashboardScreenViewModel.state =
//            state = state.copy(
//                rawBitmap = cameraUseCase.invoke(imageCapture, activity)
//            )
        }
    }
}