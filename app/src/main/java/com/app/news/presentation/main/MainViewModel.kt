package com.app.news.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.domain.usecases.app_entry.AppEntryUseCases
import com.app.news.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel for the main application logic.
 *
 * This ViewModel is responsible for determining the initial navigation
 * destination based on whether the user has completed the onboarding
 * process. It also manages the splash screen visibility.
 *
 * @param appEntryUseCases The use cases related to app entry, injected by Hilt.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    /**
     * Controls the visibility of the splash screen.
     *
     * This state variable is initially `true` and is set to `false` after
     * a short delay.
     */
    var splashCondition by mutableStateOf(true)
        private set

    /**
     * The initial navigation destination.
     *
     * This state variable determines where the user will be navigated to
     * when the app starts. It is updated based on the app entry status.
     */
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    /**
     * Initializes the ViewModel.
     *
     * This block reads the app entry status and updates the
     * `startDestination` accordingly. It also hides the splash screen after
     * a short delay.
     */
    init {
        // Read the app entry status and react to each emitted value.
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            // Update the startDestination based on the app entry status.
            startDestination = if (shouldStartFromHomeScreen) {
                Route.NewsNavigation.route
            } else {
                Route.AppStartNavigation.route
            }
            // Add a delay before hiding the splash screen.
            delay(300)
            // Hide the splash screen.
            splashCondition = false
        }.launchIn(viewModelScope) // Launch the flow collection in the viewModelScope.
    }
}