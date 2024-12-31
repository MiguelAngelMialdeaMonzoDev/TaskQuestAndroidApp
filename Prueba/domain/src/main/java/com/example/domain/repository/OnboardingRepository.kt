package com.example.domain.repository

import com.example.domain.model.OnboardingScreenModel

interface OnboardingRepository {
    fun getOnboardingScreens(): Result<List<OnboardingScreenModel>>
}