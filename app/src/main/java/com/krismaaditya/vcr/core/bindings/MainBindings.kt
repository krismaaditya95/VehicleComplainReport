package com.krismaaditya.vcr.core.bindings

import com.krismaaditya.vcr.main.data.repository.AddReportRepositoryImpl
import com.krismaaditya.vcr.main.data.repository.CameraRepositoryImpl
import com.krismaaditya.vcr.main.data.repository.ReportListRepositoryImpl
import com.krismaaditya.vcr.main.data.repository.VehicleListRepositoryImpl
import com.krismaaditya.vcr.main.domain.repository.AddReportRepository
import com.krismaaditya.vcr.main.domain.repository.CameraRepository
import com.krismaaditya.vcr.main.domain.repository.ReportListRepository
import com.krismaaditya.vcr.main.domain.repository.VehicleListRepository
import com.krismaaditya.vcr.main.domain.usecase.AddReportUseCase
import com.krismaaditya.vcr.main.domain.usecase.CameraUseCase
import com.krismaaditya.vcr.main.domain.usecase.GetAllReportUseCase
import com.krismaaditya.vcr.main.domain.usecase.GetAllVehicleUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mainBindings = module {


    single {

        HttpClient(CIO){

            expectSuccess = true

            install(HttpTimeout){
                requestTimeoutMillis = 60000
                connectTimeoutMillis = 60000
            }

            install(ContentNegotiation){
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(DefaultRequest){
                header(HttpHeaders.ContentType, ContentType.Any)
            }

            install(Logging){
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }

                level = LogLevel.ALL
            }
        }
    }

    singleOf(::VehicleListRepositoryImpl).bind<VehicleListRepository>()
    singleOf(::ReportListRepositoryImpl).bind<ReportListRepository>()
    singleOf(::CameraRepositoryImpl).bind<CameraRepository>()
    singleOf(::AddReportRepositoryImpl).bind<AddReportRepository>()

    single{
        GetAllVehicleUseCase(get())
    }

    single{
        GetAllReportUseCase(get())
    }

    single{
        CameraUseCase(get())
    }

    single{
        AddReportUseCase(get())
    }
}