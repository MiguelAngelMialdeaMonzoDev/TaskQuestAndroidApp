package com.example.data.repository

import com.example.domain.model.OnboardingScreenModel
import com.example.domain.repository.OnboardingRepository
import com.example.domain.common.Result
import com.example.domain.exception.DomainException

class OnboardingRepositoryImpl(private val onboardingScreens: List<OnboardingScreenModel>)
    : OnboardingRepository {
    override fun getOnboardingScreens(): Result<List<OnboardingScreenModel>> {
        return try {
            if (onboardingScreens.isEmpty()) {
                Result.Error(Exception("No onboarding screens available"))
            } else {
                Result.Success(onboardingScreens)
            }
        } catch (e: Exception) {
            Result.Error(DomainException.UnexpectedException(e))
        }
    }
}