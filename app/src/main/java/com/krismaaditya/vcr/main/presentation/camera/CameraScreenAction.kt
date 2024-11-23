package com.krismaaditya.vcr.main.presentation.camera

import android.app.Activity
import androidx.camera.core.ImageCapture

sealed interface CameraScreenAction {

    data class TakePicture(
        val imageCapture: ImageCapture?,
        val activity: Activity,
        val callback: () -> Unit,
    ): CameraScreenAction
}