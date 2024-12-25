package com.app.news.presentation.onboarding

/**
 * Represents events that can occur on the onboarding screen.
 *
 * This sealed class defines the possible events that can be triggered
 * from the onboarding UI. It follows the MVI (Model-View-Intent) pattern,
 * where events are the "Intents" that the UI can send to the ViewModel.
 */
sealed class OnBoardingEvent {

    /**
     * Event to save the app entry status.
     *
     * This event is triggered when the user has completed the onboarding
     * process and the app should remember that it has been shown.
     */
    data object SaveAppEntry : OnBoardingEvent()
}