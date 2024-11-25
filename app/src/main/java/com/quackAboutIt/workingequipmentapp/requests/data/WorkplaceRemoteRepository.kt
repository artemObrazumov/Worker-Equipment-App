package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.core.data.constructUrl
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestDetailsResult
import com.quackAboutIt.workingequipmentapp.requests.domain.Workplace
import com.quackAboutIt.workingequipmentapp.requests.domain.WorkplaceRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.WorkplaceResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers

class WorkplaceRemoteRepository(
    private val httpClient: HttpClient
): WorkplaceRepository {

    override suspend fun getWorkplaces(token: String): WorkplaceResult {
        val response = httpClient.get(
            urlString = constructUrl("workplaces")
        ) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
        if (response.status.value in 200..299) {
            return WorkplaceResult.Success((response.body() as List<WorkplaceResponse>).map {
                it.toWorkplace()
            })
        }
        return WorkplaceResult.Success(emptyList())
    }
}