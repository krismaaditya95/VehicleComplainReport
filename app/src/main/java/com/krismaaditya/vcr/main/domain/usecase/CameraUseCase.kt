package com.krismaaditya.vcr.main.domain.usecase

import android.app.Activity
import android.graphics.Bitmap
import androidx.camera.core.ImageCapture
import androidx.camera.view.LifecycleCameraController
import com.krismaaditya.vcr.main.domain.repository.CameraRepository
import kotlinx.coroutines.flow.Flow

class CameraUseCase(
    private val cameraRepository: CameraRepository,
) {

    suspend operator fun invoke(
        imageCapture: ImageCapture?,
        activity: Activity
    ): Bitmap? {
        return cameraRepository.takePicturev2(
            imageCapture, activity
        )
    }
}