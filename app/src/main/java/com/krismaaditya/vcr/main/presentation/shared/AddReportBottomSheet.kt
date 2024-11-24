package com.krismaaditya.vcr.main.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenAction
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenState
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel
import com.krismaaditya.vcr.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReportBottomSheet(
    modifier: Modifier = Modifier,
    dashboardScreenState: DashboardScreenState,
    dashboardScreenAction: (DashboardScreenAction) -> Unit,
    navController: NavHostController,
    onTakePictureClick: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val sheetScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
            sheetScope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                if(!sheetState.isVisible){
                    dashboardScreenAction(DashboardScreenAction.HideAddReportBottomSheet)
                }
            }
        },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Row {
                Box (
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp, )
                        .clip(CircleShape)
                        .size(34.dp)
                        .background(cC73659)
                ){
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center),
                        imageVector = Icons.Rounded.Warning,
                        contentDescription = "",
                    )
                }

                Text(
                    text = "Form Laporan Keluhan",
                    color = cDC5F00,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp)
            )

            when{
                dashboardScreenState.isAllVehicleLoading ->{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
//                            .border(1.dp, cDC5F00)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(40.dp)
                                .align(Alignment.Center),
                            color = cC73659
                        )
                    }
                }

                !dashboardScreenState.isAllVehicleLoading ->{
                    AddReportForm(
                        dashboardScreenState = dashboardScreenState,
                        dashboardScreenAction = dashboardScreenAction,
                        navController = navController,
                        onTakePictureClick = onTakePictureClick
                    )
                }
            }

            Row(
                modifier = modifier.align(Alignment.End),
            ) {
                TextButton(
                    onClick = {
                        sheetScope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if(!sheetState.isVisible){
                                dashboardScreenAction(DashboardScreenAction.HideAddReportBottomSheet)
                            }
                        }
                    }
                ) {
                    Text(text = "CANCEL")
                }

//                TextButton(
//                    onClick = {
//
//                        sheetScope.launch(Dispatchers.IO) {
//                            sheetState.hide()
//                            dashboardScreenAction(DashboardScreenAction.OnFormSaved)
//                        }.invokeOnCompletion {
//                            if(!sheetState.isVisible){
//                                dashboardScreenAction(DashboardScreenAction.HideAddReportBottomSheet)
//                            }
//
//                            // load all reports
//                            dashboardScreenAction(DashboardScreenAction.LoadAllReports)
//                        }
//                    },
//                    enabled = (dashboardScreenState.vehicleList.isNotEmpty() ||
//                            dashboardScreenState.vehicleList.size > 0 ||
//                            !dashboardScreenState.isError)
//                ) {
//                    Text(text = "SAVE")
//                }
            }
        }
    }

}

@Preview
@Composable
fun AddReportBottomSheetPreview(
    modifier: Modifier = Modifier
) {
//    AddReportBottomSheet(
//
//    )
}