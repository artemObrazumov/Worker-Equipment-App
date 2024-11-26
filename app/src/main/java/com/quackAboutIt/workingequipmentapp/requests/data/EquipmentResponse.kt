package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentType
import kotlinx.serialization.Serializable

@Serializable
data class EquipmentResponse(
    val equipment: List<EquipmentItem>
)

@Serializable
data class EquipmentItem(
    val id: Long,
    val name: String,
    val image: String,
    val equipmentTypeResponses: List<EquipmentTypeResponse>
)

fun EquipmentItem.toEquipment(): Equipment {
    return Equipment(
        id = id,
        imageUrl = image,
        name = name,
        types = equipmentTypeResponses.map {
            it.toEquipmentType()
        }
    )
}

@Serializable
data class EquipmentTypeResponse(
    val id: Long,
    val type: String
)

fun EquipmentTypeResponse.toEquipmentType(): EquipmentType {
    return EquipmentType(
        id = id,
        name = type
    )
}