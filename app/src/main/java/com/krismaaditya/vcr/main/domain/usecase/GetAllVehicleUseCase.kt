package com.krismaaditya.vcr.main.domain.usecase

import com.krismaaditya.vcr.main.domain.entity.VehicleEntity
import com.krismaaditya.vcr.main.domain.repository.VehicleListRepository
import com.krismaaditya.vcr.main.domain.repository.VehicleListResult
import kotlinx.coroutines.flow.Flow

class GetAllVehicleUseCase(
    private val vehicleListRepository: VehicleListRepository
) {

    suspend operator fun invoke(): Flow<VehicleListResult<List<VehicleEntity>>> {
        return vehicleListRepository.getVehicleList()
    }
}