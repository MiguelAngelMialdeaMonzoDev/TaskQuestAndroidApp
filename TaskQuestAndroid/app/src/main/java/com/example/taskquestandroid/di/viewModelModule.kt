package com.example.taskquestandroid.di

import com.example.taskquestandroid.features.onboarding.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OnboardingViewModel() }
    /*viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { TaskViewModel(get()) }
    viewModel { GoalsViewModel(get()) }
    viewModel { ProfileViewModel(get()) }*/
}