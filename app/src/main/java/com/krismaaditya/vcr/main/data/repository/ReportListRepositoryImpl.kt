package com.krismaaditya.vcr.main.data.repository

import com.krismaaditya.vcr.core.constants.EndPoints
import com.krismaaditya.vcr.core.constants.Keys
import com.krismaaditya.vcr.main.data.data_sources.remote.ReportDto
import com.krismaaditya.vcr.main.data.mapper.toReportEntity
import com.krismaaditya.vcr.main.domain.entity.ReportEntity
import com.krismaaditya.vcr.main.domain.repository.ReportListRepository
import com.krismaaditya.vcr.main.domain.repository.ReportListResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReportListRepositoryImpl(
    private val httpClient: HttpClient
): ReportListRepository {


    private suspend fun getReportListFromRemote(): List<ReportEntity>{
        val reportList: List<ReportDto> = httpClient.get(EndPoints.mainUrl + EndPoints.getAllReport){
            parameter(key = "userId", value = Keys.userId)
        }.body()
        return reportList.map { it.toReportEntity() }
    }

    override suspend fun getReportList(): Flow<ReportListResult<List<ReportEntity>>> {
        return flow {
            val remoteReportList = try {
                getReportListFromRemote()
            }catch (e: Exception){
                e.printStackTrace()
                null
            }

            remoteReportList?.let {
                emit(ReportListResult.Success(remoteReportList))
                return@flow
            }
        }
    }
}