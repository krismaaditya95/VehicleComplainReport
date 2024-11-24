package com.krismaaditya.vcr.main.presentation.dashboard

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.krismaaditya.vcr.config.ScreenRoutes
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel.Companion.CAMERA_PERMISSION
import com.krismaaditya.vcr.main.presentation.shared.AddReportBottomBar
import com.krismaaditya.vcr.main.presentation.shared.CustomTopBar
import com.krismaaditya.vcr.main.presentation.shared.ReportItemWidget
import com.krismaaditya.vcr.ui.theme.*

@Composable
fun DashboardScreen(
    viewModel: DashboardScreenViewModel,
    navController: NavHostController,
//    onTakePictureClick: () -> Unit,
    activity: Activity
) {
    LaunchedEffect(key1 = true) {
        viewModel.onAction(DashboardScreenAction.LoadAllReports)
    }
    
    DashboardOverviewCoreScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        navController = navController,
        onTakePictureClick = {

            if(!viewModel.arePermissionGranted(activity)){

                Log.d("ARE PERMISSION GRANTED = ", viewModel.arePermissionGranted(activity).toString())
                ActivityCompat.requestPermissions(
                    activity,
                    CAMERA_PERMISSION,
                    10
                )
            }else{
                Log.d("[MASUK KE ELSE] ARE PERMISSION GRANTED = ", viewModel.arePermissionGranted(activity).toString())
                navController.navigate(ScreenRoutes.CameraScreen)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardOverviewCoreScreen(
    state: DashboardScreenState,
    onAction: (DashboardScreenAction) -> Unit,
    navController: NavHostController,
    onTakePictureClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.fillMaxWidth(),
                appBarTitle = "Hai, ${state.currentUser}"
            )
        },
        bottomBar = {
            AddReportBottomBar(
                dashboardScreenAction = onAction,
                dashboardScreenState = state,
                navController = navController,
                onTakePictureClick = onTakePictureClick
            )
        }
    ) { innerPadding ->

        when{
            state.isAllReportLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(40.dp)
                            .align(Alignment.Center),
                        color = cDC5F00
                    )
                }
            }

            !state.isAllReportLoading -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
//                        .height(400.dp)
                        .padding(start = 14.dp, end = 14.dp,
                            top = innerPadding.calculateTopPadding(),
                            bottom = innerPadding.calculateBottomPadding()),
//                            .border(1.dp, cDC5F00),
                    contentPadding = PaddingValues(top = 14.dp)
                ) {
                    itemsIndexed(state.reportList){ index, item ->
                        ReportItemWidget(
                            reportItem = item
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardOverviewScreenPreview(modifier: Modifier = Modifier) {
    DashboardOverviewCoreScreen(
        state = DashboardScreenState(),
        onAction = {},
        navController = rememberNavController(),
        onTakePictureClick = {}
    )
}