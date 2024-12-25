package com.app.news

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.app.news.domain.usecases.AppEntryUseCases
import com.app.news.presentation.onboarding.OnBoardingScreen
import com.app.news.presentation.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The main activity of the application.
 *
 * This activity is responsible for setting up the UI and displaying the
 * initial screen of the app.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * The use cases related to app entry.
     *
     * This property is injected by Hilt and provides access to the
     * ReadAppEntry and SaveAppEntry use cases.
     */
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install the splash screen.
        installSplashScreen()

        // Enable edge-to-edge display.
        enableEdgeToEdge()

        // Launch a coroutine in the activity's lifecycle scope to read the app entry status.
        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                // Log the app entry status.
                Log.d("TAG", "onCreate: $it")
            }
        }
        // Set the content of the activity using Jetpack Compose.
        setContent {
            // Apply the app's theme.
            NewsAppTheme {
                // Display the onboarding screen.
                OnBoardingScreen(
                    modifier = Modifier
                        .fillMaxSize() // Make the onboarding screen fill the available space.
                )
            }
        }
    }
}