package com.app.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.app.news.presentation.onboarding.OnBoardingScreen
import com.app.news.ui.theme.NewsAppTheme

/**
 * The main activity of the application.
 *
 * This activity is responsible for setting up the UI and displaying the
 * initial screen of the app.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting.
     *
     * This is where most initialization should go: calling `setContentView`
     * to inflate the activity's UI, using `findViewById` to programmatically
     * interact with widgets in the UI, and setting up listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in [onSaveInstanceState].
     */
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
                // Use a Scaffold to provide a basic layout structure.
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> // Make the scaffold fill the available space.
                    // Display the onboarding screen.
                    OnBoardingScreen(
                        modifier = Modifier
                            .fillMaxSize() // Make the onboarding screen fill the available space.
                            .padding(innerPadding) // Apply the inner padding provided by the Scaffold.
                    )
                }
            }
        }
    }
}