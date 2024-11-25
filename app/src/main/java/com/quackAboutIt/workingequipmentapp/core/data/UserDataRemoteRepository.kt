package com.quackAboutIt.workingequipmentapp.core.data

import com.quackAboutIt.workingequipmentapp.auth.domain.LoginRequest
import com.quackAboutIt.workingequipmentapp.core.domain.Unit
import com.quackAboutIt.workingequipmentapp.core.domain.UserData
import com.quackAboutIt.workingequipmentapp.core.domain.UserDataRepository
import com.quackAboutIt.workingequipmentapp.requests.data.RequestResponse
import com.quackAboutIt.workingequipmentapp.requests.data.toRequest
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestListResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserDataRemoteRepository(
    private val httpClient: HttpClient
): UserDataRepository {

    private var userData: UserData? = null

    override suspend fun getUserData(token: String): UserData? {
        if (userData == null) {
            val response = httpClient.get(
                urlString = constructUrl("users")
            ) {
                headers {
                    append("Authorization", "Bearer $token")
                }
            }
            if (response.status.value in 200..299) {
                userData = (response.body() as UserDataResponse).toUserData()
            }
        }
        return userData
    }
}