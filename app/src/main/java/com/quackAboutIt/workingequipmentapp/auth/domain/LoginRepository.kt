package com.quackAboutIt.workingequipmentapp.auth.domain

interface LoginRepository {

    suspend fun doLogin(email: String, password: String): LoginResult
}