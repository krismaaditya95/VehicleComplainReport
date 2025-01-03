package com.krismaaditya.vcr.main.presentation.shared

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenAction
import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenState
import com.krismaaditya.vcr.ui.theme.cC73659
import com.krismaaditya.vcr.ui.theme.cDC5F00
import com.krismaaditya.vcr.ui.theme.cEEEEEE
import com.krismaaditya.vcr.ui.theme.cmykGreen
import com.krismaaditya.vcr.ui.theme.cmykRed


@Composable
fun AddReportForm(
    modifier: Modifier = Modifier,
    dashboardScreenState: DashboardScreenState,
    dashboardScreenAction: (DashboardScreenAction) -> Unit,
    navController: NavHostController,
    onTakePictureClick: () -> Unit
) {

    Column {
        // -----------------------
        // READ ONLY CURRENT DATE
        // -----------------------
        OutlinedTextField(
            value = dashboardScreenState.currentDate,
            onValueChange = {

            },
            label = {
                Text(text = "Date")
            },
            readOnly = true,
            leadingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date"
                    )
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(60.dp),
//            colors = OutlinedTextFieldDefaults.colors(
//                unfocusedBorderColor = cmykGreen,
//                focusedBorderColor = cmykGreen
//            )
        )


        // -------------------
        // Dropdown Vehicle
        // -------------------
        var isExpanded by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = dashboardScreenState.selectedVehicle.type,
            onValueChange = {

            },
            readOnly = true,
            leadingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DirectionsCar,
                        contentDescription = "Selected Vehicle"
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Selected Vehicle"
                    )
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .pointerInput(
                    dashboardScreenState.selectedVehicle
                ) {
                    awaitEachGesture {
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (upEvent != null) {
                            isExpanded = !isExpanded
                        }
                    }
                }
                .height(60.dp),
//            colors = OutlinedTextFieldDefaults.colors(
//                unfocusedBorderColor = cmykGreen,
//                focusedBorderColor = cmykGreen
//            )
        )
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            dashboardScreenState.vehicleList.forEachIndexed { index, vehicleEntity ->
                DropdownMenuItem(
                    text = {
                        Text(text = vehicleEntity.type)
                    },
                    onClick = {
                        dashboardScreenAction(DashboardScreenAction.OnVehicleSelected(
                            vehicle = vehicleEntity)
                        )
                        isExpanded = false
                    }
                )
            }
        }


        // -------------------
        // Keluhan Text Field
        // -------------------
        OutlinedTextField(
            value = dashboardScreenState.note,
            label = {
                Text(text = "Catatan Keluhan :")
            },
//            prefix = {
//                Row {
//                    IconButton(
//                        onClick = {},
//                        modifier = Modifier
//                            .align(Alignment.Top)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Edit,
//                            contentDescription = "Date"
//                        )
//                    }
//
//                    Text(text = "Catatan Keluhan :")
//                }
//            },

            onValueChange = { newValue ->
                dashboardScreenAction(DashboardScreenAction.OnReportNoteValueChanged(newValue))
            },
            readOnly = false,
//            leadingIcon = {
//                Row {
//                    IconButton(
//                        onClick = {},
//                        modifier = Modifier
//                            .align(Alignment.Top)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Edit,
//                            contentDescription = "Date"
//                        )
//                    }
//                }
//            },
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(160.dp),
//            colors = OutlinedTextFieldDefaults.colors(
//                unfocusedBorderColor = cmykGreen,
//                focusedBorderColor = cmykGreen
//            ),
//            supportingText = {
//
//            }
        )

        // ----------------
        // Dokumen Laporan
        // ----------------
        Text(
            text = "Dokumen Laporan",
            color = cDC5F00,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
        ){
            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .border(1.dp, cDC5F00, shape = RoundedCornerShape(4.dp)),
                model = dashboardScreenState.imageUri,
                contentDescription = "",
            )

            Column {
                Button(
                    onClick = {
                        onTakePictureClick()
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .padding(start = 14.dp),
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor = cmykRed
                        ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Ambil Foto",
                        color = cEEEEEE,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // --------------------
        // SUBMIT FORM BUTTON
        // --------------------
        Button(
            onClick = {
                dashboardScreenAction(DashboardScreenAction.OnFormSubmitted)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 14.dp, bottom = 14.dp),
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = cmykRed
                ),
            shape = RoundedCornerShape(8.dp),
            enabled =

            !dashboardScreenState.isFormSubmitLoading && (dashboardScreenState.selectedVehicle.vehicleId != "" &&
                    dashboardScreenState.note != "" && dashboardScreenState.imageUri != "")
        ) {

            when{
                dashboardScreenState.isFormSubmitLoading -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(20.dp),
                            color = cC73659
                        )

                        Text(
                            text = "mengirim...",
                            color = cC73659,
                            fontSize = 16.sp
                        )

                    }
                }

                !dashboardScreenState.isFormSubmitLoading -> {
                    Text(
                        text = "Kirim Laporan",
                        color =
                        if (dashboardScreenState.selectedVehicle.vehicleId != "" &&
                                    dashboardScreenState.note != "" && dashboardScreenState.imageUri != "") cEEEEEE
                        else cmykRed,

                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


@Composable
fun AddReportFormForPreview(
    modifier: Modifier = Modifier,
//    dashboardScreenState: DashboardScreenState,
//    dashboardScreenViewModel: DashboardScreenViewModel,
//    dashboardScreenAction: (DashboardScreenAction) -> Unit = dashboardScreenViewModel::onAction,
) {

    Column {
        // -----------------------
        // READ ONLY CURRENT DATE
        // -----------------------
        OutlinedTextField(
            //        value = dashboardScreenState.currentDate,
            value = "asdasd",
            onValueChange = {

            },
            label = {
                Text(text = "Date")
            },
            readOnly = true,
            leadingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date"
                    )
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, bottom = 10.dp)
                .height(60.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = cmykGreen,
                focusedBorderColor = cmykGreen
            )
        )


        // -------------------
        // Dropdown Vehicle
        // -------------------
        var isExpanded by remember { mutableStateOf(false) }

        OutlinedTextField(
            //        value = dashboardScreenState.currentDate,
            value = "Pilih Kendaraan",
            onValueChange = {

            },
            readOnly = true,
            leadingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DirectionsCar,
                        contentDescription = "Vehicle"
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Date"
                    )
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, bottom = 10.dp)
                .pointerInput(
                    ""
                ) {
                    awaitEachGesture {
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (upEvent != null) {
                            isExpanded = !isExpanded
                        }
                    }
                }
                .height(60.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = cmykGreen,
                focusedBorderColor = cmykGreen
            )
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            
        }


        // -------------------
        // Keluhan Text Field
        // -------------------
        OutlinedTextField(
            //        value = dashboardScreenState.currentDate,
            value = "",
//            label = {
//                Text(text = "Catatan Keluhan :")
//            },
            prefix = {
                Text(text = "Catatan Keluhan :")
            },

            onValueChange = {

            },
            readOnly = false,
            leadingIcon = {
                Row {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .align(Alignment.Top)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Date"
                        )
                    }
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, bottom = 10.dp)
                .height(160.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = cmykGreen,
                focusedBorderColor = cmykGreen
            )
        )

        // ----------------
        // Dokumen Laporan
        // ----------------
        Text(
            text = "Dokumen Laporan",
            color = cDC5F00,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 14.dp, top = 8.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
        ){
            AsyncImage(
                modifier = Modifier
                    .padding(start = 14.dp)
                    .width(100.dp)
                    .height(100.dp)
                    .border(1.dp, cDC5F00, shape = RoundedCornerShape(4.dp)),
                model = "https://storage.googleapis.com/fleetifyid_images_staging/android/YPAj9mvmMNRnpR7yJ0IIk5teTWwYkl/REPORT-80123228.jpg",
//                model = reportItem.photo,
                contentDescription = "",
            )

            Column {
                Button(
                    onClick = {
                        //                onAddReportClick()
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .padding(start = 14.dp),
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor = cmykRed
                        ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Ambil Foto",
                        color = cEEEEEE,
                        fontSize = 12.sp
                    )
                }

                Button(
                    onClick = {
                        //                onAddReportClick()
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .padding(start = 14.dp),
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor = cmykRed
                        ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Ambil Dari Galeri",
                        color = cEEEEEE,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // --------------------
        // SUBMIT FORM BUTTON
        // --------------------
        Button(
            onClick = {
//                onAddReportClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(14.dp),
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = cmykRed
                ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Kirim Laporan",
                color = cEEEEEE,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun AddReportFormForPreviewPreview(
    modifier: Modifier = Modifier,
) {

    AddReportFormForPreview()

}