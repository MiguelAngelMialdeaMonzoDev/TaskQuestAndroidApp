package com.miguelmialdea.taskquest.data.di.modules

import org.koin.dsl.module

val databaseModule = module {
    /*single {
        Room.databaseBuilder(
            get(),
            TaskQuestDatabase::class.java,
            "taskquest_database"
        ).build()
    }
    single { get<TaskQuestDatabase>().questDao() }
    single { get<TaskQuestDatabase>().epicQuestDao() }
    single { get<TaskQuestDatabase>().userDao() }
    single { get<TaskQuestDatabase>().userStatsDao() }*/
}