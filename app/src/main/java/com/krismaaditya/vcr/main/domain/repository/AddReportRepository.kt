package com.krismaaditya.vcr.main.domain.repository

import com.krismaaditya.vcr.main.domain.entity.AddReportEntity
import kotlinx.coroutines.flow.Flow

interface AddReportRepository {

    suspend fun addReport(
        addReportEntity: AddReportEntity
    ): Flow<AddReportResult<String>>
}