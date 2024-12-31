package com.app.news.presentation.details

/**
 * Represents events that can occur on the details screen.
 *
 * This sealed class defines the possible events that can be triggered
 * from the details UI. It follows the MVI (Model-View-Intent) pattern,
 * where events are the "Intents" that the UI can send to the ViewModel.
 */
sealed class DetailsEvent {

    /**
     * Event to save an article.
     *
     * This event is triggered when the user wants to save the currently
     * displayed article for later viewing.
     */
    data object SaveArticle : DetailsEvent()
}