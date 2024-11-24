package com.krismaaditya.vcr.main.presentation.camera

import android.app.Activity
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenAction
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenState
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel
import com.krismaaditya.vcr.ui.theme.cDC5F00
import com.krismaaditya.vcr.ui.theme.cmykRed
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.tooling.preview.Preview as ComposePreview


@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
    activity: Activity,
    dashboardScreenViewModel: DashboardScreenViewModel,
    state: DashboardScreenState = dashboardScreenViewModel.state,
    onImageCaptured: () -> Unit
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
            .fillMaxWidth()
            .height(472.dp)
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
//                            .setCaptureMode(CAPT)
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

            when{
                state.isCaptureLoading ->{
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(40.dp)
                            .align(Alignment.CenterVertically),
                        color = cDC5F00
                    )
                }

                !state.isCaptureLoading ->{
                    FloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        onClick = {
                            Toast.makeText(activity.applicationContext, "Capturing...", Toast.LENGTH_LONG).show()
                            dashboardScreenViewModel.onAction(DashboardScreenAction.TakePicture(
                                imageCapture = imageCapture,
                                activity = activity,
                                callback = onImageCaptured
                            ))
                        },
                        containerColor = cDC5F00
                    ) {

                        Icon(
                            imageVector = Icons.Filled.CameraAlt,
                            contentDescription = "Take Picture"
                        )
                    }

//                    Text(
//                        text = "Bitmap : ${dashboardScreenViewModel.state.rawBitmap}",
//                        color = cmykRed
//                    )
                }
            }

//            FloatingActionButton(
//                modifier = Modifier
//                    .align(Alignment.CenterVertically),
//                onClick = {
//                    Toast.makeText(activity.applicationContext, "Capturing...", Toast.LENGTH_LONG).show()
//                    dashboardScreenViewModel.onAction(DashboardScreenAction.TakePicture(
//                        imageCapture = imageCapture,
//                        activity = activity
//                    ))
//                },
//                containerColor = cDC5F00
//            ) {
//
//                Icon(
//                    imageVector = Icons.Filled.CameraAlt,
//                    contentDescription = "Take Picture"
//                )
//            }
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