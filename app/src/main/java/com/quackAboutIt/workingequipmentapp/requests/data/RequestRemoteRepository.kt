package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.core.data.constructUrl
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentInRequestDetails
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestDetails
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestDetailsResult
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestListResult
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestSendResult
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.ZonedDateTime

class RequestRemoteRepository(
    private val httpClient: HttpClient
): RequestRepository {

    override suspend fun getRequests(token: String): RequestListResult {
        val response = httpClient.get(
            urlString = constructUrl("requests")
        ) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
        if (response.status.value in 200..299) {
            return RequestListResult.Success((response.body() as List<RequestResponse>).map {
                it.toRequest()
            })
        }
        return RequestListResult.Success(
            emptyList()
        )
    }

    override suspend fun sendRequest(requestDTO: RequestDTO): RequestSendResult {
        delay(2000L)
        return RequestSendResult.Success
    }

    override suspend fun getRequestDetails(token: String, requestId: Long): RequestDetailsResult {
        val response = httpClient.get(
            urlString = constructUrl("requests/$requestId")
        ) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
        if (response.status.value in 200..299) {
            return RequestDetailsResult.Success((response.body() as RequestDetailsResponse).toRequestDetails())
        }
        throw Exception()
    }
}