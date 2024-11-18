package com.quackAboutIt.workingequipmentapp.core.data

import com.quackAboutIt.workingequipmentapp.core.domain.Unit
import com.quackAboutIt.workingequipmentapp.core.domain.UserData
import com.quackAboutIt.workingequipmentapp.core.domain.UserDataRepository

class UserDataRemoteRepository: UserDataRepository {

    private var userData: UserData? = null

    override suspend fun getUserData(token: String): UserData? {
        if (userData == null) {
            userData = UserData(
                id = -1,
                name = "Artem",
                unit = Unit(
                    id = -1,
                    address = "какаято пизда"
                )
            )
        }
        return userData
    }
}