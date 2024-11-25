package com.quackAboutIt.workingequipmentapp.auth.data

import com.quackAboutIt.workingequipmentapp.auth.domain.LoginRepository
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginRequest
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginResponse
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginResult
import com.quackAboutIt.workingequipmentapp.core.data.constructUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LoginRemoteRepository(
    private val httpClient: HttpClient
) : LoginRepository {

    override suspend fun doLogin(email: String, password: String): LoginResult {
        val response = httpClient.post(
            urlString = constructUrl("auth/sign-in")
        ) {
            contentType(ContentType.Application.Json)
            setBody(
                LoginRequest(
                    username = email,
                    password = password
                )
            )
        }
        if (response.status.value in 200..299) {
            return LoginResult.Success(LoginResponse(token = (response.body() as LoginResponse).token))
        }
        return LoginResult.Failure("Неправильный логин или пароль")
    }
}