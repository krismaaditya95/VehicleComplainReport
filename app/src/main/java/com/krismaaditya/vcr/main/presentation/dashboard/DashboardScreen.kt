package com.krismaaditya.vcr.main.presentation.dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krismaaditya.vcr.main.presentation.shared.AddReportBottomBar
import com.krismaaditya.vcr.main.presentation.shared.CustomTopBar
import com.krismaaditya.vcr.main.presentation.shared.ReportItemWidget
import com.krismaaditya.vcr.ui.theme.*

@Composable
fun DashboardScreen(
    viewModel: DashboardScreenViewModel
) {
    LaunchedEffect(key1 = true) {
        viewModel.onAction(DashboardScreenAction.LoadAllReports)
    }
    
    DashboardOverviewCoreScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardOverviewCoreScreen(
    state: DashboardScreenState,
    onAction: (DashboardScreenAction) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomBar = {
            AddReportBottomBar()
        }
    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .padding(innerPadding)
//                .fillMaxSize()
//                .border(1.dp, cDC5F00)
//        ){
//            Spacer(modifier = Modifier.height(4.dp))
//
//            when{
//                state.isLoading -> {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight()
//                    ){
//                        CircularProgressIndicator(
//                            modifier = Modifier
//                                .width(40.dp)
//                                .align(Alignment.Center),
//                            color = cDC5F00
//                        )
//                    }
//                }
//
//                !state.isLoading -> {
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxWidth()
////                            .fillMaxHeight()
//                            .height(400.dp)
//                            .padding(start = 14.dp, end = 14.dp),
////                            .border(1.dp, cDC5F00),
//                        contentPadding = PaddingValues(top = 14.dp)
//                    ) {
//                        itemsIndexed(state.reportList){ index, item ->
//                            ReportItemWidget(
//                                modifier = Modifier.fillParentMaxHeight(),
//                                reportItem = item
//                            )
//                        }
//                    }
//                }
//            }
//        }

        when{
            state.isLoading -> {
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

            !state.isLoading -> {
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
    )
}