package com.krismaaditya.vcr.main.domain.repository

sealed class ReportListResult<T>(
    val data: T? = null,
    val error: String?
) {
    class Success<T>(data: T?): ReportListResult<T>(data, null)
    class Error<T>(error: String?): ReportListResult<T>(null, error)
}