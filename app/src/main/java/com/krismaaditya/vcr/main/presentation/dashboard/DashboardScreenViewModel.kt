package com.krismaaditya.vcr.main.presentation.dashboard

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krismaaditya.vcr.core.constants.Keys
import com.krismaaditya.vcr.core.utils.StringDateFormatter
import com.krismaaditya.vcr.main.domain.entity.AddReportEntity
import com.krismaaditya.vcr.main.domain.entity.VehicleEntity
import com.krismaaditya.vcr.main.domain.repository.AddReportResult
import com.krismaaditya.vcr.main.domain.repository.ReportListResult
import com.krismaaditya.vcr.main.domain.repository.VehicleListResult
import com.krismaaditya.vcr.main.domain.usecase.AddReportUseCase
import com.krismaaditya.vcr.main.domain.usecase.CameraUseCase
import com.krismaaditya.vcr.main.domain.usecase.GetAllReportUseCase
import com.krismaaditya.vcr.main.domain.usecase.GetAllVehicleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.time.ZonedDateTime


class DashboardScreenViewModel(
    private val appContext: Context,
    private val getAllVehicleUseCase: GetAllVehicleUseCase,
    private val getAllReportUseCase: GetAllReportUseCase,
    private val cameraUseCase: CameraUseCase,
    private val addReportUseCase: AddReportUseCase
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
            DashboardScreenAction.OnFormSubmitted -> onFormSubmitted()
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

            is DashboardScreenAction.OnVehicleSelected -> onVehicleSelected(
                selectedVehicle = action.vehicle
            )
        }
    }

    companion object {
        val CAMERA_PERMISSION = mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        ).apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
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

//    private fun takePicture(
//        imageCapture: ImageCapture?,
//        activity: Activity,
//        callback: () -> Unit
//    ){
//        viewModelScope.launch {
//
//            Log.d("DASHBOARD VIEMODEL", "=> takePicture()")
//
//            state = state.copy(
//                isCaptureLoading = true,
//                rawBitmap = null
//            )
//
//            val name = "TEMP"
//
//            val contentValues = ContentValues().apply {
//                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
//                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
//                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/VCR")
//                }
//            }
//
//            if (imageCapture != null) {
//                Log.d("IMAGE CAPTURE = ", imageCapture.resolutionInfo.toString())
//            }
//
//            imageCapture?.takePicture(
//                ContextCompat.getMainExecutor(activity),
//
//                object : ImageCapture.OnImageCapturedCallback() {
//
//                    override fun onCaptureStarted() {
//                        super.onCaptureStarted()
//                        Log.d("IMAGE CAPTURE STARTED = ", "MULAI")
//                    }
//
//                    @ExperimentalGetImage
//                    override fun onCaptureSuccess(image: ImageProxy) {
//                        super.onCaptureSuccess(image)
//
//                        Log.d("IMAGE CAPTURE SUCCESS = ", image.toString())
//                        Log.d("IMAGE FORMAT = ", image.image?.format.toString())
//
//                        Toast.makeText(activity.applicationContext, "IMAGE CAPTURE SUKSES!", Toast.LENGTH_LONG).show()
//
//                        val matrix = Matrix().apply {
//                            postRotate(image.imageInfo.rotationDegrees.toFloat())
//                        }
//
//                        val newBitmap = Bitmap.createBitmap(
//                            image.toBitmap(),
//                            0, 0,
//                            image.width, image.height,
//                            matrix, true
//                        )
//
//                        Log.d("BITMAP WIDTH = ", newBitmap.width.toString())
//                        Log.d("BITMAP HEIGHT = ", newBitmap.height.toString())
//
//                        state = state.copy(
//                            rawBitmap = newBitmap,
//                            capturedImage = image.image
//                        )
//
//                        Log.d("NEW BITMAP = ", newBitmap.toString())
//
//                        state = state.copy(
//                            isCaptureLoading = false
//                        )
//
//                        callback()
//                    }
//                },
//            )
//
//
//        }
//    }

    private fun onVehicleSelected(selectedVehicle: VehicleEntity){
        state = state.copy(
            selectedVehicle = selectedVehicle
        )
    }

    private fun takePicture(
        imageCapture: ImageCapture?,
        activity: Activity,
        callback: () -> Unit
    ){
        viewModelScope.launch {

            Log.d("DASHBOARD VIEMODEL", "=> takePicture()")

            state = state.copy(
                isCaptureLoading = true,
                imageUri = ""
            )

            val name = "TEMP - ${StringDateFormatter.toDayMonthYearAtHourMinute(ZonedDateTime.now())}"

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

            val outputOptions = ImageCapture.OutputFileOptions
                .Builder(
                    activity.contentResolver,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                ).build()

            imageCapture?.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(activity),
                object : ImageCapture.OnImageSavedCallback{

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        Log.d("OnImageSaved SUCCESS", outputFileResults.savedUri.toString())
//                        Toast.makeText(activity.applicationContext, "OnImageSaved SUCCESS : ${outputFileResults.savedUri}"
//                            , Toast.LENGTH_LONG).show()

                        val fullPath: List<String> = getImagePathFromUri(outputFileResults.savedUri!!, activity).split("/")

                        val fileName = fullPath.get(fullPath.size - 1)
                        Toast.makeText(activity.applicationContext, "OnImageSaved SUCCESS : $fileName"
                            , Toast.LENGTH_LONG).show()

                        state = state.copy(
//                            imageUri = outputFileResults.savedUri.toString(),
                            imageUri = getImagePathFromUri(outputFileResults.savedUri!!, activity),
                            isCaptureLoading = false
                        )
                        callback()
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Log.d("OnImageSaved ERROR", exception.message.toString())
                        Toast.makeText(activity.applicationContext, "OnImageSaved ERROR", Toast.LENGTH_LONG).show()

                        state = state.copy(
                            isCaptureLoading = false
                        )
                        callback()
                    }
                }
            )


        }
    }

    private fun getImagePathFromUri(
        uri: Uri,
        activity: Activity
    ): String{
        val contentResolver: ContentResolver = activity.applicationContext.contentResolver

        var cursor: Cursor? = null

        try{
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = contentResolver.query(uri, proj, null, null, null)

            val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

            cursor?.moveToFirst()

            if (cursor != null) {
                return cursor.getString(columnIndex!!)
            }
        } finally {
            cursor?.close()
        }

        return ""
    }

    private fun validate(): Boolean{
        var valid = false

        if(state.selectedVehicle.vehicleId != ""){
            valid = true
        }

        if(state.imageUri != ""){
            valid = true
        }

        return valid
    }

    private fun onFormSubmitted(){
        viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(
                isFormSubmitLoading = true
            )

            val reportFormToSubmit = AddReportEntity(
                vehicleId = state.selectedVehicle.vehicleId,
                userId = Keys.userId,
                note = state.note,
                photo = state.imageUri
            )

            addReportUseCase(reportFormToSubmit).collect{ result ->

                Log.d("FORM SUBMIT RESPONSE => ", result.data.toString())

                state = when(result){

                    is AddReportResult.Error -> {
                        state.copy(
                            isFormSubmitError = true
                        )
                    }
                    is AddReportResult.Success -> {
                        state.copy(
                            formSubmitResponse = result.data ?: "null response"
                        )
                    }
                }
            }

            state = state.copy(
                isFormSubmitLoading = false
            )
        }
    }
}