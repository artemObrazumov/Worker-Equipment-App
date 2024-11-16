package com.quackAboutIt.workingequipmentapp.auth.domain

interface CredentialsRepository {

    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
}