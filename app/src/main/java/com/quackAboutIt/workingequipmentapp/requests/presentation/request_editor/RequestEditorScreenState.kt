package com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor

import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment
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
        val arrivalDate: ZonedDateTime = ZonedDateTime.now(),
        val equipment: List<EquipmentInRequestState> = emptyList(),
        val isWorkplaceDialogOpened: Boolean = false,
        val isEquipmentDialogOpened: Boolean = false
    ) : RequestEditorScreenState()
}

data class EquipmentInRequestState(
    val equipmentId: Long,
    val quantity: Int,
    val arrivalTime: ZonedDateTime,
    val workHours: Int,
    val workMinutes: Int
)

fun Equipment.toState(): EquipmentInRequestState {
    return EquipmentInRequestState(
        equipmentId = this.id,
        quantity = 0,
        arrivalTime = ZonedDateTime.now().plusHours(1),
        workHours = 0,
        workMinutes = 0
    )
}