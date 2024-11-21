package com.krismaaditya.vcr.main.domain.repository

sealed class VehicleListResult<T>(
    val data: T? = null,
    val error: String?
) {
    class Success<T>(data: T?): VehicleListResult<T>(data, null)
    class Error<T>(error: String?): VehicleListResult<T>(null, error)
}