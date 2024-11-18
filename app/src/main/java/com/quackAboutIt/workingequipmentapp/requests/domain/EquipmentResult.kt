package com.quackAboutIt.workingequipmentapp.requests.domain

sealed class EquipmentResult {

    data class Success(
        val equipment: List<Equipment>
    ): EquipmentResult()
}