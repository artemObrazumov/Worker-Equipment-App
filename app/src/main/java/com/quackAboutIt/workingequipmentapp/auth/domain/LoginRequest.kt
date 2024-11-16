package com.quackAboutIt.workingequipmentapp.auth.domain

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
