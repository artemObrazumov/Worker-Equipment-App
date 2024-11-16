package com.quackAboutIt.workingequipmentapp.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginRepository
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginResult
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val loginRepository: LoginRepository,
    private val credentialsRepository: CredentialsRepository
): ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            LoginScreenState()
        )

    fun updateEmail(email: String) {
        _state.update {
            it.copy(
                email = email
            )
        }
    }

    fun updatePassword(password: String) {
        _state.update {
            it.copy(
                password = password
            )
        }
    }

    fun doLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value.isLoading) {
                return@launch
            }
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val result = loginRepository.doLogin(
                _state.value.email,
                _state.value.password
            )
            when (result) {
                is LoginResult.Success -> {
                    credentialsRepository.saveToken(result.response.token)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            hasLoggedIn = true
                        )
                    }
                }
                is LoginResult.Failure -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }
}