package com.krismaaditya.vcr.main.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.NoteAlt
import androidx.compose.material.icons.rounded.Person4
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
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
                .height(340.dp)
                .padding(bottom = 10.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column {

                // -------------------
                // TOP SECTION
                // -------------------
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier
                            .weight(0.7f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

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
                                fontSize = 10.sp,
                                color = cC73659
                            )
                        }
                    }


                    Column(
                        modifier = Modifier
                            .weight(0.3f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = StringDateFormatter.toDayMonthYearAtHourMinute(reportItem.createdAt),
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        Box (
                            modifier = Modifier
                                .padding(end = 10.dp, start = 10.dp)
                                .clip((RoundedCornerShape(5.dp)))
                                .fillMaxWidth()
                                .background(cmykGreen),
                        ){
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = reportItem.reportStatus,
                                textAlign = TextAlign.Center,
                                color = cEEEEEE,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                            )
                        }
                    }
                }
                HorizontalDivider()

                // -------------------
                // MIDDLE SECTION
                // -------------------
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier
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
                                    modifier = Modifier
                                        .padding(end = 8.dp),
                                    imageVector = Icons.Rounded.DirectionsCar,
                                    contentDescription = ""
                                )
                                Text(
                                    text = reportItem.vehicleName,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
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
                                    modifier = Modifier
                                        .padding(end = 8.dp),
                                    imageVector = Icons.Rounded.Person4,
                                    contentDescription = ""
                                )
                                Text(
                                    text = reportItem.createdBy,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
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
                    ) {
                        Text(
                            modifier = Modifier
                                .border(1.dp, cDC5F00, shape = RoundedCornerShape(4.dp))
                                .align(Alignment.Center)
                                .padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 6.dp),
                            text = reportItem.vehicleLicenseNumber,
                            textAlign = TextAlign.Center,
                            color = cDC5F00,
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
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    Row(
                        modifier = Modifier
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
                                    .align(Alignment.Top)
                                    .padding(end = 8.dp),
                                imageVector = Icons.Rounded.NoteAlt,
                                contentDescription = ""
                            )
                            Column {
                                Text(
                                    text = "Catatan Keluhan :",
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(bottom = 4.dp)
                                )

                                Text(
                                    text = reportItem.note,
                                    fontSize = 12.sp,
                                    maxLines = 3,
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
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize(),
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