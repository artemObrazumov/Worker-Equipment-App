package com.quackAboutIt.workingequipmentapp.requests.data

import java.time.Duration
import java.time.LocalDateTime

data class RequestDTO(
    val unitId: Long,
    val workplaceId: Long,
    val distance: Double,
    val arrivalDate: LocalDateTime,
    val equipmentInRequest: List<EquipmentInRequestDTO>
)

data class EquipmentInRequestDTO(
    val equipmentId: Long,
    val equipmentTypeId: Long,
    val arrivalTime: LocalDateTime,
    val quantity: Int,
    val duration: Duration
)