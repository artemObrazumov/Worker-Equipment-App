package com.quackAboutIt.workingequipmentapp.requests.domain

import java.time.Duration
import java.time.ZonedDateTime

data class RequestDetails(
    val id: Long,
    val state: RequestState,
    val workerName: String,
    val unitAddress: String,
    val workplaceAddress: String,
    val distance: Double,
    val arrivalDate: ZonedDateTime,
    val progress: Int? = null,
    val total: Int? = null,
    val equipmentInRequestDetails: List<EquipmentInRequestDetails>
)

data class EquipmentInRequestDetails(
    val id: Long,
    val image: String,
    val name: String,
    val number: String,
    val arrivalTime: ZonedDateTime,
    val duration: Duration
)