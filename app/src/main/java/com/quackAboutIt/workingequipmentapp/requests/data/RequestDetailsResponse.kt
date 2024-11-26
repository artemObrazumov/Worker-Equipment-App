package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.core.domain.DurationSerializer
import com.quackAboutIt.workingequipmentapp.core.domain.LocalDateTimeSerializer
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentInRequestDetails
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestDetails
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState
import kotlinx.serialization.Serializable
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

@Serializable
data class RequestDetailsResponse(
    val id: Long,
    val state: String,
    val workerName: String,
    val unitAddress: String,
    val workplaceAddress: String,
    val distance: Double,
    @Serializable(LocalDateTimeSerializer::class)
    val date: LocalDateTime,
    val progress: Int?,
    val total: Int?,
    val equipment: List<EquipmentInDetailsResponse>
)

fun RequestDetailsResponse.toRequestDetails(): RequestDetails {
    return RequestDetails(
        id = id,
        state = RequestState.CURRENT,
        workerName = workerName,
        unitAddress = unitAddress,
        workplaceAddress = workplaceAddress,
        distance = distance,
        arrivalDate = date.atZone(ZoneId.systemDefault()),
        equipmentInRequestDetails = equipment.map {
            it.toEquipmentInRequestDetails()
        },
        progress = progress,
        total = total
    )
}

@Serializable
data class EquipmentInDetailsResponse(
    val id: Long,
    val equipmentImage: String,
    val equipmentName: String,
    val licensePlateNumber: String?,
    @Serializable(LocalDateTimeSerializer::class)
    val arrivalTime: LocalDateTime,
    @Serializable(DurationSerializer::class)
    val workDuration: Duration
)

fun EquipmentInDetailsResponse.toEquipmentInRequestDetails(): EquipmentInRequestDetails {
    return EquipmentInRequestDetails(
        id = id.toLong(),
        image = equipmentImage,
        name = equipmentName,
        number = licensePlateNumber ?: "не определен",
        arrivalTime = arrivalTime.atZone(ZoneId.systemDefault()),
        duration = workDuration
    )
}