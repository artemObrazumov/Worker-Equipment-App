package com.quackAboutIt.workingequipmentapp.core.data

import com.quackAboutIt.workingequipmentapp.core.domain.UserData
import kotlinx.serialization.Serializable

@Serializable
data class UserDataResponse(
    val id: Long,
    val name: String,
    val unit: UnitResponse
)

fun UserDataResponse.toUserData(): UserData {
    return UserData(
        id = id,
        name = name,
        unit = unit.toUnit()
    )
}