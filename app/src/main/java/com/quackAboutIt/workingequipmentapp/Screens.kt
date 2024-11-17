package com.quackAboutIt.workingequipmentapp

import kotlinx.serialization.Serializable

@Serializable
data object Login

@Serializable
data object Requests

@Serializable
data object Notifications

@Serializable
data class RequestDetails(
    val id: Long
)

@Serializable
data object RequestEditor