package com.quackAboutIt.workingequipmentapp.auth.presentation.login

data class LoginScreenState (
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val hasLoggedIn: Boolean = false
)