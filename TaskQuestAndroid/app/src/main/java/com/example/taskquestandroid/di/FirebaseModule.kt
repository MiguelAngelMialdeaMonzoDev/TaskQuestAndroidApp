package com.example.taskquestandroid.di

import com.google.firebase.Firebase
import org.koin.dsl.module

val firebaseModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }
}