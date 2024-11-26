package com.quackAboutIt.workingequipmentapp.core.data

import kotlinx.serialization.Serializable

@Serializable
data class UnitResponse(
    val id: Long,
    val address: String
)

fun UnitResponse.toUnit(): com.quackAboutIt.workingequipmentapp.core.domain.Unit {
    return com.quackAboutIt.workingequipmentapp.core.domain.Unit(
        id = id,
        address = address
    )
}