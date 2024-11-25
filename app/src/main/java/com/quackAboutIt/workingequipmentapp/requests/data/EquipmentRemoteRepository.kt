package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.core.data.constructUrl
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentRepository
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*

class EquipmentRemoteRepository(
    private val httpClient: HttpClient
): EquipmentRepository {

    override suspend fun getEquipment(token: String): EquipmentResult {
        val response = httpClient.get(
            urlString = constructUrl("equipment/types")
        ) {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
        if (response.status.value in 200..299) {
            return EquipmentResult.Success((response.body() as List<EquipmentItem>).map {
                it.toEquipment()
            })
        }
        return EquipmentResult.Success(emptyList())
    }
}