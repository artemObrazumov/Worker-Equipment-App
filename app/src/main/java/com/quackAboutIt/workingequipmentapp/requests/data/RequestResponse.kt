package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.core.domain.LocalDateTimeSerializer
import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.ZoneId

@Serializable
data class RequestResponse(
    val id: Long,
    val state: String,
    val workplaceAddress: String,
    val equipmentCount: Int,
    @Serializable(LocalDateTimeSerializer::class)
    val date: LocalDateTime,
    val progress: Int? = null,
    val total: Int? = null
)

fun RequestResponse.toRequest(): Request {
    return Request(
        id = id,
        state = RequestState.CURRENT,
        workplaceAddress = workplaceAddress,
        equipmentCount = equipmentCount,
        date = date.atZone(ZoneId.systemDefault()),
        progress = progress,
        total = total
    )
}