package com.krismaaditya.vcr.main.domain.repository

sealed class AddReportResult<T>(
    val data: T? = null,
    val error: String?
) {
    class Success<T>(data: T?): AddReportResult<T>(data, null)
    class Error<T>(error: String?): AddReportResult<T>(null, error)
}