package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment

sealed class EquipmentResult {

    data class Success(
        val equipment: List<Equipment>
    ): EquipmentResult()
}