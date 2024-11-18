package com.quackAboutIt.workingequipmentapp.requests.presentation.equipment_list

import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment

data class EquipmentListScreenState(
    val isLoading: Boolean = true,
    val equipment: List<Equipment> = emptyList()
)