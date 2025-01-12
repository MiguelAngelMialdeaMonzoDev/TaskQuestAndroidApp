package com.example.taskquestandroid.di

import org.koin.dsl.module

val databaseModule = module {
    /*single {
        Room.databaseBuilder(
            androidContext(),
            TaskQuestDatabase::class.java,
            "taskquest.db"
        ).build()
    }

    // DAOs
    single { get<TaskQuestDatabase>().taskDao() }
    single { get<TaskQuestDatabase>().goalDao() }
    single { get<TaskQuestDatabase>().userDao() }*/
}