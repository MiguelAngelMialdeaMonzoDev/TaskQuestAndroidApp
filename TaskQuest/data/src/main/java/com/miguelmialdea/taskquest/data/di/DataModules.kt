package com.miguelmialdea.taskquest.data.di

import com.miguelmialdea.taskquest.data.di.modules.dataSourceModule
import com.miguelmialdea.taskquest.data.di.modules.databaseModule
import com.miguelmialdea.taskquest.data.di.modules.mongoModule
import com.miguelmialdea.taskquest.data.di.modules.repositoryModule

val dataModules = listOf(
    databaseModule,
    repositoryModule,
    mongoModule,
    dataSourceModule
)