package com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor

import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentType
import java.time.ZonedDateTime

sealed class RequestEditorScreenState {
    data object Loading : RequestEditorScreenState()
    data class Content(
        val isLoading: Boolean = false,
        val workerName: String = "",
        val unitId: Long = -1,
        val unitAddress: String = "",
        val workplaceId: Long = -1,
        val workplaceAddress: String = "test",
        val distanceString: String = "0.0",
        val distance: Double = 0.0,
        val arrivalDate: ZonedDateTime = ZonedDateTime.now(),
        val equipment: List<EquipmentInRequestState> = emptyList(),
        val isWorkplaceDialogOpened: Boolean = false,
        val isEquipmentDialogOpened: Boolean = false,
        val isEquipmentDetailsDialogOpened: Boolean = false
    ) : RequestEditorScreenState()
}

data class EquipmentInRequestState(
    val equipmentId: Long,
    val equipmentName: String,
    val equipmentType: EquipmentType,
    val image: String,
    val types: List<EquipmentType>,
    val quantity: Int,
    val arrivalTime: ZonedDateTime,
    val workHours: Int,
    val workMinutes: Int
)

fun Equipment.toState(): EquipmentInRequestState {
    return EquipmentInRequestState(
        equipmentId = id,
        image = imageUrl,
        types = types,
        quantity = 0,
        arrivalTime = ZonedDateTime.now().plusHours(1),
        workHours = 0,
        workMinutes = 0,
        equipmentName = name,
        equipmentType = types.first()
    )
}