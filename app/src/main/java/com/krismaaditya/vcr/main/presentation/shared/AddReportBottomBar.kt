package com.krismaaditya.vcr.main.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenAction
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenState
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel
import com.krismaaditya.vcr.ui.theme.*

@Composable
fun AddReportBottomBar(
    modifier: Modifier = Modifier,
    dashboardScreenState: DashboardScreenState,
    dashboardScreenAction: (DashboardScreenAction) -> Unit,
) {

    BottomAppBar(
        modifier = Modifier
    ){
        Button(
            onClick = {
                dashboardScreenAction(DashboardScreenAction.ShowAddReportBottomSheet)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp),
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = cmykRed
                ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Buat Laporan Keluhan Baru",
                color = cEEEEEE,
                fontSize = 16.sp
            )
        }

        if(dashboardScreenState.isAddReportBottomSheetVisible){
            AddReportBottomSheet(
                dashboardScreenState = dashboardScreenState,
                dashboardScreenAction = dashboardScreenAction
            )
        }
    }

}