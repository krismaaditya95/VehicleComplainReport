package com.krismaaditya.vcr.main.domain.repository

import android.app.Activity
import android.app.Application
import android.graphics.Bitmap
import androidx.camera.core.ImageCapture
import androidx.camera.view.LifecycleCameraController
import kotlinx.coroutines.flow.Flow

interface CameraRepository{

//    suspend fun takePicture(
//        controller: LifecycleCameraController
//    )

//    suspend fun takePicturev2(
//        imageCapture: ImageCapture?,
//        activity: Activity
//    ): Bitmap?

    suspend fun takePicturev2(
        imageCapture: ImageCapture?,
        activity: Activity
    ): Flow<CameraResult<Bitmap>>

}