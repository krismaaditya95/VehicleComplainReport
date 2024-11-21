package com.krismaaditya.vcr.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.krismaaditya.vcr.config.ScreenRoutes
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreen
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel
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
//                MainActivityCoreScreen(
//                    navController = navController
//                )

                Navigator(
                    modifier = Modifier
                        .fillMaxSize(),
                    navController = navController,
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
    dashboardScreenViewModel: DashboardScreenViewModel = koinViewModel()
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
            )
        }
    }

}