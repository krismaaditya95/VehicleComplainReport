package com.krismaaditya.vcr.main.data.repository

import com.krismaaditya.vcr.core.constants.EndPoints
import com.krismaaditya.vcr.main.domain.entity.AddReportEntity
import com.krismaaditya.vcr.main.domain.repository.AddReportRepository
import com.krismaaditya.vcr.main.domain.repository.AddReportResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.append
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.io.File

class AddReportRepositoryImpl(
    private val httpClient: HttpClient
): AddReportRepository {

    override suspend fun addReport(
        addReportEntity: AddReportEntity
    ): Flow<AddReportResult<String>> {

        return flow {



            val response = try{


//                httpClient.post(
//                    EndPoints.mainUrl + EndPoints.addLaporan
//                ) {
////                    headers {
////                        append(HttpHeaders.ContentType, "multipart/form-data")
////                    }
//
//                    val fullPath: List<String> = addReportEntity.photo.split("/")
//
//                    val fileName = fullPath.get(fullPath.size - 1)
//
//                    val fileByteArray: ByteArray = File(addReportEntity.photo).readBytes()
//
//
//
//                    contentType(ContentType.MultiPart.FormData)
//
//                    setBody(MultiPartFormDataContent(
//                        formData {
//                            append("vehicleId", addReportEntity.vehicleId,
////                                headers = Headers.build {
////                                    append(HttpHeaders.ContentType, "text/plain")
////                                }
//                            )
//                            append("note", addReportEntity.note,
////                                headers = Headers.build {
////                                    append(HttpHeaders.ContentType, "text/plain")
////                                }
//                            )
//                            append("userId", addReportEntity.userId,
////                                headers = Headers.build {
////                                    append(HttpHeaders.ContentType, "text/plain")
////                                }
//                            )
//                            append("photo",
//                                fileByteArray,
//                                headers = Headers.build {
//                                    append(HttpHeaders.ContentType, "image/jpg")
//                                    append(HttpHeaders.ContentDisposition, "filename = image.jpg")
//                                }
//                            )
//                        },
//                        boundary = "WebAppBoundary"
//                    ))
//
//                    onUpload { bytesSentTotal, contentLength ->
//                        println("Sent $bytesSentTotal bytes from $contentLength")
//                    }
//                }.body()

                val fullPath: List<String> = addReportEntity.photo.split("/")
                val fileName = fullPath.get(fullPath.size - 1)
                val fileByteArray: ByteArray = File(addReportEntity.photo).readBytes()

                httpClient.submitFormWithBinaryData(
                    url = EndPoints.mainUrl + EndPoints.addLaporan,
                    formData = formData {
                        append("\"vehicleId\"", "\"${addReportEntity.vehicleId}\"",
                            Headers.build {
                                append(HttpHeaders.ContentType, "multipart/form-data")
//                                \"ktor_logo.png\"
//                                append(HttpHeaders.ContentDisposition, "name = \"$fileName\"")
                            }
                        )

                        append("\"note\"", "\"${addReportEntity.note}\"",
                            Headers.build {
                                append(HttpHeaders.ContentType, "multipart/form-data")
//                                \"ktor_logo.png\"
//                                append(HttpHeaders.ContentDisposition, "name = \"$fileName\"")
                            }
                        )
                        append("\"userId\"", "\"${addReportEntity.userId}\"",
                            Headers.build {
                                append(HttpHeaders.ContentType, "multipart/form-data")
//                                \"ktor_logo.png\"
//                                append(HttpHeaders.ContentDisposition, "name = \"$fileName\"")
                            }
                        )
                        append("\"photo\"", fileByteArray,
                            Headers.build {
                                append(HttpHeaders.ContentType, "multipart/form-data")
//                                \"ktor_logo.png\"
                                append(HttpHeaders.ContentDisposition, "attachment")
                                append(HttpHeaders.ContentDisposition, "filename = \"$fileName\"")
                            }
                        )
                    }
                )
            }catch (e: Exception){
                e.printStackTrace()
                println("EXCEPTION => ${e.message.toString()}")
            }


            response.let {
                emit(AddReportResult.Success(response.toString()))
                return@flow
            }
        }
    }
}