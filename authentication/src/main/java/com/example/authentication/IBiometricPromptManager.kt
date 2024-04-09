package com.example.authentication

import kotlinx.coroutines.flow.Flow

interface IBiometricPromptManager {
    fun showBiometricPrompt()
    val promptResult: Flow<BiometricPromptManager.BiometricResult>
}