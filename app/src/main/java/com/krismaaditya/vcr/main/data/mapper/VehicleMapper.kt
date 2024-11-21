package com.krismaaditya.vcr.main.data.mapper

import com.krismaaditya.vcr.main.data.data_sources.remote.VehicleDto
import com.krismaaditya.vcr.main.domain.entity.VehicleEntity

fun VehicleDto.toVehicleEntity(): VehicleEntity = VehicleEntity(
    licenseNumber = licenseNumber,
    type = type,
    vehicleId = vehicleId
)

//fun VehicleListDto.toVehicleListEntity(): VehicleListEntity = VehicleListEntity(
//    vehicleList = vehicleList.map { it.toVehicleEntity() }
//)