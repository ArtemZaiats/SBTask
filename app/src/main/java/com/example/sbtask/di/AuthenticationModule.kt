package com.example.sbtask.di

import android.content.Context
import com.example.authentication.BiometricPromptManager
import com.example.authentication.IBiometricPromptManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class AuthenticationModule {

    @Provides
    fun providesBiometricPromptManager(@ActivityContext activity: Context): IBiometricPromptManager {
        return BiometricPromptManager(activity)
    }
}