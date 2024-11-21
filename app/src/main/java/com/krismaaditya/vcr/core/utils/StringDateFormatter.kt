package com.krismaaditya.vcr.core.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class StringDateFormatter{
    companion object{
        fun toStandardDateFormat(date: ZonedDateTime): String{
            val formattedZonedDateTime = DateTimeFormatter
                .ofPattern("dd MMM yyyy").format(date)

            return formattedZonedDateTime.toString()
        }

        fun toDayMonthYearAtHourMinute(date: ZonedDateTime): String{
            val formattedDayMonthYear = DateTimeFormatter
                .ofPattern("dd MMM yyyy").format(date)

            val formatted24HourMinutes = DateTimeFormatter
                .ofPattern("HH:mm").format(date)

            return "$formattedDayMonthYear at $formatted24HourMinutes"
        }

        fun toDayMonthYearAtHourMinute(dateString: String): String{

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val localDateTime = LocalDateTime.parse(dateString, formatter)
            val zonedDateTime = localDateTime.atOffset(ZoneOffset.UTC).atZoneSameInstant(ZoneId.systemDefault())

            val formattedDayMonthYear = DateTimeFormatter
                .ofPattern("dd MMM").format(zonedDateTime)

            val formatted24HourMinutes = DateTimeFormatter
                .ofPattern("HH:mm").format(zonedDateTime)

            return "${zonedDateTime.dayOfWeek}, $formattedDayMonthYear - $formatted24HourMinutes"
        }
    }
}