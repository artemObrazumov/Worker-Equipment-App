package com.quackAboutIt.workingequipmentapp

import androidx.lifecycle.ViewModel
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.core.domain.UserDataRepository

class MainActivityViewModel(
    private val credentialsRepository: CredentialsRepository,
    private val userDataRepository: UserDataRepository
): ViewModel() {

    //fun
}