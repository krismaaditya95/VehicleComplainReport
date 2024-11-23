package com.krismaaditya.vcr.main.presentation.dashboard

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krismaaditya.vcr.main.domain.repository.ReportListResult
import com.krismaaditya.vcr.main.domain.repository.VehicleListResult
import com.krismaaditya.vcr.main.domain.usecase.CameraUseCase
import com.krismaaditya.vcr.main.domain.usecase.GetAllReportUseCase
import com.krismaaditya.vcr.main.domain.usecase.GetAllVehicleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardScreenViewModel(
    private val appContext: Context,
    private val getAllVehicleUseCase: GetAllVehicleUseCase,
    private val getAllReportUseCase: GetAllReportUseCase,
    private val cameraUseCase: CameraUseCase
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
            is DashboardScreenAction.TakePicture -> takePicture(
                imageCapture = action.imageCapture,
                activity = action.activity,
                callback = action.callback
            )
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

//    private fun takePicture(
//        imageCapture: ImageCapture?,
//        activity: Activity
//    ){
//        viewModelScope.launch(Dispatchers.IO) {
//
//            Log.d("DASHBOARD VIEMODEL", "=> takePicture()")
//
//            cameraUseCase(
//                imageCapture,
//                activity
//            ).collect{ result ->
//                Log.d("COLLECT RESULT => ", "=> ${result.data.toString()}")
//
//                when(result){
//                    is CameraResult.Error -> {
//                        state = state.copy(
//                            isCaptureError = true
//                        )
//                        Log.d("CameraResult.ERROR => ", "=> ${result.error.toString()}")
//                    }
//                    is CameraResult.Success -> {
//                        state = state.copy(
//                            rawBitmap = result.data
//                        )
//                        Log.d("CameraResult.SUCCESS => ", "=> HEIGHT : ${result.data?.height.toString()}")
//                    }
//                }
//            }
//        }
//    }

    private fun takePicture(
        imageCapture: ImageCapture?,
        activity: Activity,
        callback: () -> Unit
    ){
        viewModelScope.launch {

            Log.d("DASHBOARD VIEMODEL", "=> takePicture()")

            state = state.copy(
                isCaptureLoading = true,
                rawBitmap = null
            )

            val name = "TEMP"

            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/VCR")
                }
            }

            if (imageCapture != null) {
                Log.d("IMAGE CAPTURE = ", imageCapture.resolutionInfo.toString())
            }

            imageCapture?.takePicture(
                ContextCompat.getMainExecutor(activity),
                object : ImageCapture.OnImageCapturedCallback() {

                    override fun onCaptureStarted() {
                        super.onCaptureStarted()
                        Log.d("IMAGE CAPTURE STARTED = ", "MULAI")
                    }

                    @ExperimentalGetImage
                    override fun onCaptureSuccess(image: ImageProxy) {
                        super.onCaptureSuccess(image)

                        Log.d("IMAGE CAPTURE SUCCESS = ", image.toString())
                        Log.d("IMAGE FORMAT = ", image.image?.format.toString())

                        Toast.makeText(activity.applicationContext, "IMAGE CAPTURE SUKSES!", Toast.LENGTH_LONG).show()

                        val matrix = Matrix().apply {
                            postRotate(image.imageInfo.rotationDegrees.toFloat())
                        }

                        val newBitmap = Bitmap.createBitmap(
                            image.toBitmap(),
                            0, 0,
                            image.width, image.height,
                            matrix, true
                        )

                        Log.d("BITMAP WIDTH = ", newBitmap.width.toString())
                        Log.d("BITMAP HEIGHT = ", newBitmap.height.toString())

                        state = state.copy(
                            rawBitmap = newBitmap
                        )

                        Log.d("NEW BITMAP = ", newBitmap.toString())

                        state = state.copy(
                            isCaptureLoading = false
                        )

                        callback()
                    }
                },
            )


        }
    }
}