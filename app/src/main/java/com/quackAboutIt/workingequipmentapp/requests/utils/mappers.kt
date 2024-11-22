package com.quackAboutIt.workingequipmentapp.requests.utils

import com.quackAboutIt.workingequipmentapp.requests.data.EquipmentInRequestDTO
import com.quackAboutIt.workingequipmentapp.requests.data.RequestDTO
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.EquipmentInRequestState
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.RequestEditorScreenState
import java.time.Duration

fun RequestEditorScreenState.Content.toRequestDTO(): RequestDTO {
    val equipmentInRequest = equipment.map {
        it.toEquipmentInRequestDTO()
    }
    return RequestDTO(
        unitId = this.unitId,
        workplaceId = this.workplaceId,
        distance = this.distanceString.toDoubleOrNull() ?: 0.0,
        arrivalDate = this.arrivalDate.toLocalDateTime(),
        equipmentInRequest = equipmentInRequest
    )
}

fun EquipmentInRequestState.toEquipmentInRequestDTO(): EquipmentInRequestDTO {
    return EquipmentInRequestDTO(
        equipmentId = equipmentId,
        equipmentTypeId = equipmentType.id,
        arrivalTime = arrivalTime.toLocalDateTime(),
        quantity = quantity,
        duration = Duration
            .ofHours(workHours.toLong())
            .plusMinutes(workMinutes.toLong())
    )
}