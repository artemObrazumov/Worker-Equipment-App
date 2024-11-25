package com.quackAboutIt.workingequipmentapp.auth.domain

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)
