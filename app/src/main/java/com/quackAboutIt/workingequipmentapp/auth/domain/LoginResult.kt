package com.quackAboutIt.workingequipmentapp.auth.domain

sealed class LoginResult {

    data class Failure(
        val message: String
    ): LoginResult()

    data class Success(
        val response: LoginResponse
    ): LoginResult()
}