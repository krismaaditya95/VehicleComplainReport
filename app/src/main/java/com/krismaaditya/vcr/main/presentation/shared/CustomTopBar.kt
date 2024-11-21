package com.krismaaditya.vcr.main.presentation.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krismaaditya.vcr.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar (
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    appBarTitle: String = "Vehicle Complain Report",
    enableBackNavigationButton: Boolean = false
){
    TopAppBar(
        title = {
            Box {
                Text(
                    text = appBarTitle,
                    fontSize = 24.sp,
                    color = cDC5F00,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp, bottom = 6.dp)
                        .align(Alignment.CenterStart)
                )
            }
        },
        navigationIcon = {
            if(enableBackNavigationButton){
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to previous screen"
                    )
                }
            }
        },
        actions = {
            Box (
                modifier = Modifier
                    .padding(end = 10.dp, start = 10.dp)
                    .clip(CircleShape)
                    .size(34.dp)
                    .background(cC73659)
            ){
//                Image(
//                    painter = painterResource(id = R.drawable.profile_picture),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(30.dp)
//                        //.clip(RoundedCornerShape(12.dp))
//                        .align(Alignment.Center)
//                )

                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Report"
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomTopBarPreview(modifier: Modifier = Modifier) {
    CustomTopBar(
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
            state = rememberTopAppBarState()
        )
    )
}