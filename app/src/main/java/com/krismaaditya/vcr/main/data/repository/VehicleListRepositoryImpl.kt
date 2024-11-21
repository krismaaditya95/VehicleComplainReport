package com.krismaaditya.vcr.main.data.repository

import com.krismaaditya.vcr.core.constants.EndPoints
import com.krismaaditya.vcr.main.data.data_sources.remote.VehicleDto
import com.krismaaditya.vcr.main.data.mapper.toVehicleEntity
import com.krismaaditya.vcr.main.domain.entity.VehicleEntity
import com.krismaaditya.vcr.main.domain.repository.VehicleListRepository
import com.krismaaditya.vcr.main.domain.repository.VehicleListResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VehicleListRepositoryImpl(
    private val httpClient: HttpClient
): VehicleListRepository {


    private suspend fun getVehicleListFromRemote(): List<VehicleEntity>{
        val vehicleList: List<VehicleDto> = httpClient.get(EndPoints.mainUrl + EndPoints.getVehicleList).body()
        return vehicleList.map { it.toVehicleEntity() }
    }

    override suspend fun getVehicleList(): Flow<VehicleListResult<List<VehicleEntity>>> {
        return flow {
            val remoteVehicleList = try {
                getVehicleListFromRemote()
            }catch (e: Exception){
                e.printStackTrace()
                null
            }

            remoteVehicleList?.let {
                emit(VehicleListResult.Success(remoteVehicleList))
                return@flow
            }
        }
    }
}