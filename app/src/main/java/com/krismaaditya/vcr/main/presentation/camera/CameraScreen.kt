package com.krismaaditya.vcr.main.presentation.camera

import android.app.Activity
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.krismaaditya.vcr.ui.theme.cDC5F00
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.tooling.preview.Preview as ComposePreview


@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
    activity: Activity,
    cameraScreenViewModel: CameraScreenViewModel = koinViewModel()
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    var imageCapture: ImageCapture? = null
    val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)
    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    val cameraController = remember {
        LifecycleCameraController(
            activity.applicationContext
        ).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .border(1.dp, cDC5F00)
    ){

        AndroidView(
            modifier = Modifier
                .border(1.dp, cDC5F00),
            factory = { it ->

                PreviewView(it).apply {
                    cameraProviderFuture.addListener({
                        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                        val preview = Preview.Builder()
                            .build()
                            .also {
                                it.setSurfaceProvider(this.surfaceProvider)
                            }

                        imageCapture = ImageCapture.Builder()
                            .build()

                        try{
                            cameraProvider.unbindAll()

                            cameraProvider.bindToLifecycle(
                                lifecycleOwner, cameraSelector, preview, imageCapture
                            )
                        }catch(e: Exception){
                            Log.d("EXCEPTION CAMERA", e.message.toString())
                        }

                    }, ContextCompat.getMainExecutor(activity))


                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 80.dp)
                .align(Alignment.BottomCenter),
        horizontalArrangement = Arrangement.Center
        ){

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = {
                    cameraScreenViewModel.onAction(CameraScreenAction.TakePicture(
                        imageCapture = imageCapture,
                        activity = activity
                    ))
                },
                containerColor = cDC5F00
            ) {

                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = "Take Picture"
                )
            }
        }
    }
}

@Composable
fun CameraScreenForPreview(
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .border(1.dp, cDC5F00)
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 80.dp)
                .align(Alignment.BottomCenter),
//            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ){

            FloatingActionButton(
//                modifier = Modifier
//                    .align(Alignment.Bottom),
                onClick = {
                },
                containerColor = cDC5F00
            ) {

                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = "Take Picture"
                )
            }
        }
    }
}


@ComposePreview
@Composable
fun CameraScreenForPreviewPreview(modifier: Modifier = Modifier) {
    CameraScreenForPreview()
}