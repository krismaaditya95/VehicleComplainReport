package com.krismaaditya.vcr.main.domain.repository

sealed class CameraResult<T>(
    val data: T? = null,
    val error: String?
) {
    class Success<T>(data: T?): CameraResult<T>(data, null)
    class Error<T>(error: String?): CameraResult<T>(null, error)
}