package com.krismaaditya.vcr.main.domain.usecase

import com.krismaaditya.vcr.main.domain.entity.ReportEntity
import com.krismaaditya.vcr.main.domain.repository.ReportListRepository
import com.krismaaditya.vcr.main.domain.repository.ReportListResult
import kotlinx.coroutines.flow.Flow

class GetAllReportUseCase(
    private val reportListRepository: ReportListRepository
) {

    suspend operator fun invoke(): Flow<ReportListResult<List<ReportEntity>>>{
        return reportListRepository.getReportList()
    }
}