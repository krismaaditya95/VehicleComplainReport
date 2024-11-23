package com.krismaaditya.vcr.core

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.krismaaditya.vcr.config.ScreenRoutes
import com.krismaaditya.vcr.main.presentation.camera.CameraScreen
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreen
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel.Companion.CAMERA_PERMISSION
import com.krismaaditya.vcr.ui.theme.VehicleComplainReportTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            VehicleComplainReportTheme {
                val navController = rememberNavController()

                Navigator(
                    modifier = Modifier
                        .fillMaxSize(),
                    navController = navController,
                    activity = this
                )
            }
        }
    }
}

//@Composable
//fun MainActivityCoreScreen(
//    navController: NavHostController,
//) {
//    val currentContext = LocalContext.current
//
//    Scaffold {
//        paddingValues ->
//
//        Navigator(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(bottom = paddingValues.calculateBottomPadding()),
//            navController = navController,
//        )
//
//    }
//}

@Composable
fun Navigator(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    dashboardScreenViewModel: DashboardScreenViewModel = koinViewModel(),
    activity: Activity
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ScreenRoutes.DashboardScreen,
        enterTransition = { slideInHorizontally { it } },
        popEnterTransition = { slideInHorizontally { -it } },
        exitTransition = { slideOutHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { -it } },

        ){

        composable<ScreenRoutes.DashboardScreen>{
            DashboardScreen(
                viewModel = dashboardScreenViewModel,
                navController = navController,
                activity = activity
//                onTakePictureClick = {
//                    if(dashboardScreenViewModel.arePermissionGranted(activity)){
//                        ActivityCompat.requestPermissions(
//                            activity,
//                            CAMERA_PERMISSION,
//                            10
//                        )
//                    }
//                }
            )
        }

        composable<ScreenRoutes.CameraScreen>{
            CameraScreen(
                activity = activity,
                dashboardScreenViewModel = dashboardScreenViewModel,
                onImageCaptured = {
                    navController.popBackStack()
                }
            )
        }
    }

}