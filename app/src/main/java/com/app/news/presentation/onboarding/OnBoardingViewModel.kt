package com.app.news.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the onboarding screen.
 *
 * This ViewModel handles events related to onboarding, such as saving the
 * app entry status. It follows the MVVM (Model-View-ViewModel) pattern,
 * where the ViewModel exposes data and actions to the View. It also uses
 * the MVI pattern by handling `OnBoardingEvent`s.
 *
 * @param appEntryUseCases The use cases related to app entry, injected by Hilt.
 */
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    /**
     * Handles the given onboarding event.
     *
     * This function is the entry point for events from the UI. It uses a
     * `when` statement to determine the appropriate action based on the
     * received event.
     *
     * @param event The onboarding event to handle.
     */
    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    /**
     * Saves the app entry status.
     *
     * This function uses the `appEntryUseCases` to save the app entry
     * status, indicating that the user has completed the onboarding process.
     * It launches a coroutine in the `viewModelScope` to perform this
     * operation asynchronously.
     */
    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}