package com.miguelmialdea.taskquest

import android.app.Application
import com.miguelmialdea.taskquest.data.di.dataModules
import com.miguelmialdea.taskquest.di.appModules
import com.miguelmialdea.taskquest.domain.di.domainModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TaskQuestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val allModules = listOf(
            appModules,
            dataModules,
            domainModules
        ).flatten()

        startKoin {
            androidLogger()
            androidContext(this@TaskQuestApplication)
            modules(*allModules.toTypedArray())
        }
    }
}