package com.quackAboutIt.workingequipmentapp.requests.domain

import androidx.compose.runtime.Immutable
import com.quackAboutIt.workingequipmentapp.core.domain.ZonedDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Immutable
data class Request(
    val id: Long,
    val state: RequestState,
    val workplaceAddress: String,
    val equipmentCount: Int,
    @Serializable(ZonedDateTimeSerializer::class)
    val date: ZonedDateTime,
    val progress: Int? = null,
    val total: Int? = null
)

enum class RequestState {
    CURRENT, FUTURE, FINISHED
}