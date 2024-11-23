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
import com.krismaaditya.vcr.main.domain.repository.CameraResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CameraRepositoryImpl(
//    private val app : Application
) : CameraRepository {

    lateinit var newBitmap: Bitmap

//    override suspend fun takePicturev2(
//        imageCapture: ImageCapture?,
//        activity: Activity,
//    ): Flow<CameraResult<Bitmap>> {
//
//        val name = "TEMP"
//
//        val contentValues = ContentValues().apply {
//            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
//            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
//                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/VCR")
//            }
//        }
//
//        if (imageCapture != null) {
//            Log.d("IMAGE CAPTURE = ", imageCapture.resolutionInfo.toString())
//        }
//
//        imageCapture?.takePicture(
//            ContextCompat.getMainExecutor(activity),
//            object : ImageCapture.OnImageCapturedCallback() {
//
//                override fun onCaptureStarted() {
//                    super.onCaptureStarted()
//                    Log.d("IMAGE CAPTURE STARTED = ", "MULAI")
//                }
//
//                override fun onCaptureSuccess(image: ImageProxy) {
//                    super.onCaptureSuccess(image)
//
//                    Log.d("IMAGE CAPTURE SUCCESS = ", image.toString())
//
//                    val matrix = Matrix().apply {
//                        postRotate(image.imageInfo.rotationDegrees.toFloat())
//                    }
//
//                    bitmapRaw = Bitmap.createBitmap(
//                        image.toBitmap(),
//                        0, 0,
//                        image.width, image.height,
//                        matrix, true
//                    )
//
//                    Log.d("BITMAP WIDTH = ", bitmapRaw?.width.toString())
//                    Log.d("BITMAP HEIGHT = ", bitmapRaw?.height.toString())
//
////                    bitmapRaw = imageBitmap
//                }
//            }
//        )
//
//        Log.d("IS BITMAP INITIALIZED = ", this::bitmapRaw.isInitialized.toString())
//
//        if(this::bitmapRaw.isInitialized){
//            return flow{
//                emit(CameraResult.Success(bitmapRaw))
//                return@flow
//            }
//        }else{
//            return flow{
//                emit(CameraResult.Error("ERROR"))
//                return@flow
//            }
//        }
//    }

    private fun proccessCapture(
        imageCapture: ImageCapture?,
        activity: Activity,
    ): Bitmap {

//        var newBitmap: Bitmap? = null
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

                    newBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0, 0,
                        image.width, image.height,
                        matrix, true
                    )

                    Log.d("BITMAP WIDTH = ", newBitmap.width.toString())
                    Log.d("BITMAP HEIGHT = ", newBitmap.height.toString())
                }
            }
        )

        return newBitmap
    }

    override suspend fun takePicturev2(
        imageCapture: ImageCapture?,
        activity: Activity,
    ): Flow<CameraResult<Bitmap>> {

        val bitmap = try{
            proccessCapture(imageCapture, activity)
        }catch (e: Exception){
            e.printStackTrace()
            null
        }

        Log.d("NEW BITMAP = ", bitmap.toString() )

        return flow {
            bitmap.let {
                emit(CameraResult.Success(bitmap))
                return@flow
            }
        }
    }


}
