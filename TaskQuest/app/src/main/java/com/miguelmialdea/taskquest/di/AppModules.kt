package com.miguelmialdea.taskquest.di

import com.miguelmialdea.taskquest.di.modules.appModule
import com.miguelmialdea.taskquest.di.modules.viewModelModule

val appModules = listOf(
    viewModelModule,
    appModule
)