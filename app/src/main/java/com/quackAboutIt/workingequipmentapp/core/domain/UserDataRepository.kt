package com.quackAboutIt.workingequipmentapp.core.domain

interface UserDataRepository {

    suspend fun getUserData(token: String): UserData?
}