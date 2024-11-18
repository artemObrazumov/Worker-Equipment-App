package com.quackAboutIt.workingequipmentapp.core.domain

sealed class UserDataResult {
    data class Success(
        val userData: UserData
    ): UserDataResult()
    data object Failure: UserDataResult()
}