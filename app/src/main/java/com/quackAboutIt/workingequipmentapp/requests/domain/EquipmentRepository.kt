package com.quackAboutIt.workingequipmentapp.requests.domain

import com.quackAboutIt.workingequipmentapp.requests.data.EquipmentResult

interface EquipmentRepository {

    suspend fun getEquipment(token: String): EquipmentResult
}