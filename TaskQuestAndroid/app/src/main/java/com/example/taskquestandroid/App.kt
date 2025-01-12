package com.example.taskquestandroid

import android.app.Application
import com.example.taskquestandroid.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TaskQuestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TaskQuestApplication)
            modules(appModules)
        }
    }
}