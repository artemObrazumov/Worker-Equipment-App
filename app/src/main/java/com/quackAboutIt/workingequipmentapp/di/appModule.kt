package com.quackAboutIt.workingequipmentapp.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.quackAboutIt.workingequipmentapp.auth.data.CredentialsLocalRepository
import com.quackAboutIt.workingequipmentapp.auth.data.LoginRemoteRepository
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginRepository
import com.quackAboutIt.workingequipmentapp.auth.presentation.login.LoginScreenViewModel
import com.quackAboutIt.workingequipmentapp.notifications.data.NotificationRemoteRepository
import com.quackAboutIt.workingequipmentapp.notifications.domain.NotificationsRepository
import com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list.NotificationListScreenViewModel
import com.quackAboutIt.workingequipmentapp.requests.data.RequestRemoteRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestRepository
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_list.RequestListScreenViewModel
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

    singleOf(::NotificationRemoteRepository).bind<NotificationsRepository>()
    viewModelOf(::NotificationListScreenViewModel)

    singleOf(::RequestRemoteRepository).bind<RequestRepository>()
    viewModelOf(::RequestListScreenViewModel)
}