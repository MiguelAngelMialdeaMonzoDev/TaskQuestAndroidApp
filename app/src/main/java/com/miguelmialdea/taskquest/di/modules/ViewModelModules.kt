package com.miguelmialdea.taskquest.di.modules

import com.miguelmialdea.taskquest.features.onboarding.OnboardingViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    //viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { OnboardingViewModel() }
}