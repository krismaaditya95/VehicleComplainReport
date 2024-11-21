package com.krismaaditya.vcr.main.domain.repository

import com.krismaaditya.vcr.main.domain.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

interface VehicleListRepository {

    suspend fun getVehicleList() : Flow<VehicleListResult<List<VehicleEntity>>>
}