package com.quackAboutIt.workingequipmentapp.requests.domain

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val id: Long,
    val state: RequestState
)

enum class RequestState {
    SENT, PROCESSED, FINISHED
}