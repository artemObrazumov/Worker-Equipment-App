package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.requests.domain.Workplace
import kotlinx.serialization.Serializable

@Serializable
data class WorkplaceResponse(
    val id: Long,
    val address: String
)

fun WorkplaceResponse.toWorkplace(): Workplace {
    return Workplace(
        id = id,
        address = address
    )
}