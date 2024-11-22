package com.quackAboutIt.workingequipmentapp.requests.domain

import com.quackAboutIt.workingequipmentapp.requests.data.RequestDTO

interface RequestRepository {

    suspend fun getRequests(token: String): RequestListResult
    suspend fun sendRequest(requestDTO: RequestDTO): RequestSendResult
    suspend fun getRequestDetails(token: String, requestId: Long): RequestDetailsResult
}