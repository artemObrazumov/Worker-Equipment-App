package com.quackAboutIt.workingequipmentapp.requests.domain

interface EquipmentRepository {

    suspend fun getEquipment(token: String): EquipmentResult
}