package com.quackAboutIt.workingequipmentapp.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.quackAboutIt.workingequipmentapp.auth.data.CredentialsLocalRepository
import com.quackAboutIt.workingequipmentapp.auth.data.LoginRemoteRepository
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginRepository
import com.quackAboutIt.workingequipmentapp.auth.presentation.login.LoginScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        PreferenceDataStoreFactory.create (
            produceFile = {
                androidContext().preferencesDataStoreFile("cred")
            }
        )
    }
    singleOf(::LoginRemoteRepository).bind<LoginRepository>()
    singleOf(::CredentialsLocalRepository).bind<CredentialsRepository>()

    viewModelOf(::LoginScreenViewModel)
}