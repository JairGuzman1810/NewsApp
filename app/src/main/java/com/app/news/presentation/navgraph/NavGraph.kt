package com.app.news.presentation.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.app.news.presentation.onboarding.OnBoardingScreen
import com.app.news.presentation.onboarding.OnBoardingViewModel

/**
 * Composable function that defines the app's navigation graph.
 *
 * This function uses Jetpack Compose Navigation to define the different
 * screens and how they connect to each other. It uses nested navigation
 * graphs to organize related screens.
 *
 * @param startDestination The initial destination of the navigation graph.
 * @param modifier The modifier to be applied to the layout.
 */
@Composable
fun NavGraph(
    startDestination: String,
    modifier: Modifier = Modifier
) {
    // Create a NavHostController to manage navigation.
    val navController = rememberNavController()

    // Define the navigation graph using NavHost.
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Define the app start navigation graph.
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            // Define the composable for the onboarding screen.
            composable(
                route = Route.OnBoardingScreen.route
            ) {
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

        // Define the news navigation graph.
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            // Define the composable for the news navigator screen.
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                Text(text = "News Navigator Screen")
            }
        }
    }
}