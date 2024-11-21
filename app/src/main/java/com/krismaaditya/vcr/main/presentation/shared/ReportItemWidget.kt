package com.krismaaditya.vcr.main.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.MailOutline
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.ShoppingCart
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.krismaaditya.vcr.core.utils.StringDateFormatter
import com.krismaaditya.vcr.main.domain.entity.ReportEntity
import com.krismaaditya.vcr.ui.theme.*

@Composable
fun ReportItemWidget(
    modifier: Modifier = Modifier,
    reportItem: ReportEntity,
) {

    Column(
        modifier = modifier
    ) {
        ElevatedCard(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(bottom = 14.dp)
                .border(1.dp, cC73659, RoundedCornerShape(4.dp)),
            shape = RoundedCornerShape(4.dp),
        ) {
            Column {

                // -------------------
                // TOP SECTION
                // -------------------
                Row(
                    modifier = Modifier
                        //                    .border(1.dp, cDC5F00)
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    //            horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(
                        modifier = Modifier
                            //                        .border(1.dp, cmykBlue)
                            .weight(0.7f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Warning,
                            contentDescription = ""
                        )

                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            Text(
                                text = "Laporan Keluhan",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                            )

                            Text(
                                text = reportItem.reportId,
                                fontSize = 10.sp
                            )
                        }
                    }


                    Column(
                        modifier = Modifier
                            //                        .border(1.dp, cmykBlue)
                            .weight(0.3f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = StringDateFormatter.toDayMonthYearAtHourMinute(reportItem.createdAt),
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        Text(
                            modifier = Modifier
                                .border(1.dp, cmykGreen)
                                .background(cmykGreen)
                                .padding(start = 20.dp, top = 2.dp, end = 20.dp, bottom = 2.dp),
                            text = reportItem.reportStatus,
                            textAlign = TextAlign.Center,
                            color = cEEEEEE,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                        )
                    }
                }
                HorizontalDivider()

                // -------------------
                // MIDDLE SECTION
                // -------------------
                Row(
                    modifier = Modifier
                        //                    .border(1.dp, cDC5F00)
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier
                            //                        .border(1.dp, cmykBlue)
                            .weight(0.7f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Sharp.ShoppingCart,
                                    contentDescription = ""
                                )
                                Text(
                                    text = reportItem.vehicleName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .padding(bottom = 4.dp)
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Sharp.Person,
                                    contentDescription = ""
                                )
                                Text(
                                    text = "Dilaporkan oleh : ${reportItem.createdBy}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .padding(bottom = 4.dp)
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.Top)
                            .weight(0.3f)
                        //                        .border(1.dp, cmykBlue)
                    ) {
                        Text(
                            modifier = Modifier
                                .border(1.dp, c151515, shape = RoundedCornerShape(4.dp))
                                .align(Alignment.Center)
                                .padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 6.dp),
                            text = reportItem.vehicleId,
                            textAlign = TextAlign.Center,
                            color = c151515,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                        )
                    }
                }
                HorizontalDivider()


                // -------------------
                // BOTTOM SECTION
                // -------------------
                Row(
                    modifier = Modifier
                        //                    .border(1.dp, cDC5F00)
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    Row(
                        modifier = Modifier
                            //                        .border(1.dp, cmykBlue)
                            .weight(0.7f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(bottom = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .align(Alignment.Top),
                                imageVector = Icons.Sharp.MailOutline,
                                contentDescription = ""
                            )
                            Column {
                                Text(
                                    text = "Catatan Keluhan :",
                                    fontSize = 14.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(bottom = 4.dp)
                                )

                                Text(
                                    text = reportItem.note,
                                    fontSize = 12.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .padding(bottom = 4.dp)
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.Top)
                            .weight(0.3f)
                        //                        .border(1.dp, cmykBlue)
                    ) {
                        AsyncImage(
                            //                        model = "https://storage.googleapis.com/fleetifyid_images_staging/android/YPAj9mvmMNRnpR7yJ0IIk5teTWwYkl/REPORT-80123228.jpg",
                            model = reportItem.photo,
                            contentDescription = "",
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun ReportItemPreview(modifier: Modifier = Modifier) {
//    ReportItemWidget(
//        reportItem = null
//    )
}