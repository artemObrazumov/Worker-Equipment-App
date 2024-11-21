package com.quackAboutIt.workingequipmentapp.requests.domain

data class Equipment(
    val id: Long,
    val imageUrl: String,
    val name: String,
    val types: List<EquipmentType>
)