package com.app.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.app.news.presentation.navgraph.NavGraph
import com.app.news.presentation.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity of the application.
 *
 * This activity is responsible for setting up the UI and displaying the
 * initial screen of the app.
 *
 * The `@AndroidEntryPoint` annotation is used to mark this class as an
 * entry point for Hilt's dependency injection. This allows Hilt to inject
 * dependencies into this activity.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * The MainViewModel instance for this activity.
     *
     * This property is obtained using the `viewModels` delegate, which
     * provides a ViewModel instance that is scoped to this activity.
     */
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install the splash screen and configure it to stay on screen while splashCondition is true.
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }

        // Enable edge-to-edge display.
        enableEdgeToEdge()

        // Set the content of the activity using Jetpack Compose.
        setContent {
            // Apply the app's theme.
            NewsAppTheme {
                // Get the start destination from the ViewModel.
                val startDestination = viewModel.startDestination
                // Display the navigation graph.
                NavGraph(
                    startDestination = startDestination
                )
            }
        }
    }
}