package com.app.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.news.presentation.onboarding.OnBoardingScreen
import com.app.news.presentation.onboarding.OnBoardingViewModel
import com.app.news.presentation.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity of the application.
 *
 * This activity is responsible for setting up the UI and displaying the
 * initial screen of the app.
 * The `@AndroidEntryPoint` annotation is used to mark this class as an
 * entry point for Hilt's dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install the splash screen.
        installSplashScreen()

        // Enable edge-to-edge display.
        enableEdgeToEdge()

        // Set the content of the activity using Jetpack Compose.
        setContent {
            // Apply the app's theme.
            NewsAppTheme {
                // Get the OnBoardingViewModel using Hilt.
                val viewModel: OnBoardingViewModel = hiltViewModel()
                // Display the onboarding screen.
                OnBoardingScreen(
                    event = viewModel::onEvent, // Pass the onEvent function reference to the OnBoardingScreen.
                    modifier = Modifier
                        .fillMaxSize() // Make the onboarding screen fill the available space.
                )
            }
        }
    }
}