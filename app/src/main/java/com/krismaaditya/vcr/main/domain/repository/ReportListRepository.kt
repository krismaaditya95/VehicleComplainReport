package com.krismaaditya.vcr.main.domain.repository

import com.krismaaditya.vcr.main.domain.entity.ReportEntity
import kotlinx.coroutines.flow.Flow

interface ReportListRepository {
    suspend fun getReportList() : Flow<ReportListResult<List<ReportEntity>>>
}