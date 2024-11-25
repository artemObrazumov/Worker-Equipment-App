package com.quackAboutIt.workingequipmentapp.auth.domain

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)
