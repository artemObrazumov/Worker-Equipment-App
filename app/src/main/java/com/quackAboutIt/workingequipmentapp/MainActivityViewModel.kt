package com.quackAboutIt.workingequipmentapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.core.domain.UserData
import com.quackAboutIt.workingequipmentapp.core.domain.UserDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val credentialsRepository: CredentialsRepository,
    private val userDataRepository: UserDataRepository
): ViewModel() {

    private val _userData: MutableStateFlow<UserData?> = MutableStateFlow(null)
    val userData = _userData
        .onStart { getUserData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            null
        )

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _userData.update {
                userDataRepository.getUserData(
                    credentialsRepository.getToken() ?: ""
                )
            }
        }
    }
}