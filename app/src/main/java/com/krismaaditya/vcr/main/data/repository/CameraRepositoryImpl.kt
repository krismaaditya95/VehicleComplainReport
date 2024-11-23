package com.krismaaditya.vcr.main.data.repository

import android.app.Activity
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.core.content.ContextCompat
import com.krismaaditya.vcr.main.domain.repository.CameraRepository

class CameraRepositoryImpl(
//    private val app : Application
) : CameraRepository {

//    override suspend fun takePicture(controller: LifecycleCameraController) {
//
//        controller.takePicture(
//            ContextCompat.getMainExecutor(app),
//
//            object : ImageCapture.OnImageCapturedCallback(){
//                override fun onCaptureSuccess(image: ImageProxy) {
//                    super.onCaptureSuccess(image)
//
//                    val matrix = Matrix().apply {
//                        postRotate(image.imageInfo.rotationDegrees.toFloat())
//                    }
//
//                    val imageBitmap = Bitmap.createBitmap(
//                        image.toBitmap(),
//                        0, 0,
//                        image.width, image.height,
//                        matrix, true
//                    )
//                }
//            }
//        )
//    }

    override suspend fun takePicturev2(
        imageCapture: ImageCapture?,
        activity: Activity,
    ): Bitmap? {
        var bitmapRaw: Bitmap? = null
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

                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    Log.d("IMAGE CAPTURE SUCCESS = ", image.toString())

                    val matrix = Matrix().apply {
                        postRotate(image.imageInfo.rotationDegrees.toFloat())
                    }

                    val imageBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0, 0,
                        image.width, image.height,
                        matrix, true
                    )

                    bitmapRaw = imageBitmap
                }
            }
        )

        return bitmapRaw
    }


}
