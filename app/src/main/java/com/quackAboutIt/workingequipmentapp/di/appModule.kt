package com.quackAboutIt.workingequipmentapp.di

import com.quackAboutIt.workingequipmentapp.auth.data.LoginRemoteRepository
import com.quackAboutIt.workingequipmentapp.auth.domain.LoginRepository
import com.quackAboutIt.workingequipmentapp.auth.presentation.login.LoginScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::LoginRemoteRepository).bind<LoginRepository>()

    viewModelOf(::LoginScreenViewModel)
}