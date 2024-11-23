package com.krismaaditya.vcr.main.domain.repository

import android.app.Activity
import android.app.Application
import android.graphics.Bitmap
import androidx.camera.core.ImageCapture
import androidx.camera.view.LifecycleCameraController

interface CameraRepository{

//    suspend fun takePicture(
//        controller: LifecycleCameraController
//    )

    suspend fun takePicturev2(
        imageCapture: ImageCapture?,
        activity: Activity
    ): Bitmap?

}