package com.quackAboutIt.workingequipmentapp.auth.data

import com.quackAboutIt.workingequipmentapp.auth.domain.LoginRepository
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginResponse
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginResult
import kotlinx.coroutines.delay

class LoginRemoteRepository: LoginRepository {

    override suspend fun doLogin(email: String, password: String): LoginResult {
        delay(2000L)
        return LoginResult.Success(LoginResponse(token = "123"))
    }
}