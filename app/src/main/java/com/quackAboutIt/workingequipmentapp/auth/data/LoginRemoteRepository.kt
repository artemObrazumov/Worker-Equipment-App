package com.quackAboutIt.workingequipmentapp.auth.data

import com.quackAboutIt.workingequipmentapp.auth.domain.LoginRepository
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginResponse
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginResult

class LoginRemoteRepository: LoginRepository {

    override fun doLogin(email: String, password: String): LoginResult {
        return LoginResult.Success(LoginResponse(token = "123"))
    }
}