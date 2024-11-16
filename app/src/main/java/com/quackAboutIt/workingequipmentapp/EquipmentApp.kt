package com.quackAboutIt.workingequipmentapp

import android.app.Application
import com.quackAboutIt.workingequipmentapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EquipmentApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EquipmentApp)
            androidLogger()

            modules(appModule)
        }
    }
}