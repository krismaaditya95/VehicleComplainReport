package com.krismaaditya.vcr.main.domain.usecase

import com.krismaaditya.vcr.main.domain.entity.AddReportEntity
import com.krismaaditya.vcr.main.domain.repository.AddReportRepository
import com.krismaaditya.vcr.main.domain.repository.AddReportResult
import kotlinx.coroutines.flow.Flow

class AddReportUseCase(
    private val addReportRepository: AddReportRepository
) {
    suspend operator fun invoke(
        addReportEntity: AddReportEntity
    ): Flow<AddReportResult<String>>{
        return addReportRepository.addReport(addReportEntity)
    }
}