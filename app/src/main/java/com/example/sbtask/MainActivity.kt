package com.example.sbtask

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontVariation
import androidx.navigation.compose.rememberNavController
import com.example.sbtask.ui.authentication.BiometricPromptManager
import com.example.sbtask.ui.navigation.AppNavHost
import com.example.sbtask.ui.navigation.BottomNavigationBar
import com.example.sbtask.ui.theme.SBTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val promptManager by lazy {
        BiometricPromptManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val biometricResult by promptManager.promptResult.collectAsState(initial = null)
                    val enrollLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult(),
                        onResult = {
                            println("Activity result: $it")
                        }
                    )

                    LaunchedEffect(biometricResult) {
                        if (biometricResult is BiometricPromptManager.BiometricResult.AuthenticationNotSet) {
                            if (Build.VERSION.SDK_INT >= 30) {
                                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                                    putExtra(
                                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                                    )
                                }
                                enrollLauncher.launch(enrollIntent)
                            }
                        }
                    }

                    promptManager.showBiometricPrompt(
                        title = "Scan to login",
                        description = ""
                    )

                    biometricResult?.let { result ->
                        when (result) {
                            is BiometricPromptManager.BiometricResult.AuthenticationError -> TODO()
                            BiometricPromptManager.BiometricResult.AuthenticationFailed -> TODO()
                            BiometricPromptManager.BiometricResult.AuthenticationNotSet -> TODO()
                            BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                                Scaffold(
                                    bottomBar = { BottomNavigationBar(navController = navController) }
                                ) { paddingValues ->
                                    Box(
                                        modifier = Modifier
                                            .padding(paddingValues)
                                    ) {
                                        AppNavHost(navController = navController)
                                    }
                                }
                            }

                            BiometricPromptManager.BiometricResult.FeatureUnavailable -> TODO()
                            BiometricPromptManager.BiometricResult.HardwareUnavailable -> TODO()
                        }
                    }
                }
            }
        }
    }
}
