package com.quackAboutIt.workingequipmentapp.auth.domain

interface LoginRepository {

    fun doLogin(email: String, password: String): LoginResult
}