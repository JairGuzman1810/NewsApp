package com.app.news.presentation.details

import com.app.news.domain.model.Article

/**
 * Represents events that can occur on the details screen.
 *
 * This sealed class defines the possible events that can be triggered
 * from the details UI. It follows the MVI (Model-View-Intent) pattern,
 * where events are the "Intents" that the UI can send to the ViewModel.
 */
sealed class DetailsEvent {

    /**
     * Event to save or delete an article.
     *
     * This event is triggered when the user wants to save the currently
     * displayed article for later viewing or delete it from their saved articles.
     * The ViewModel will determine whether to save or delete based on the
     * current state of the article in the database.
     *
     * @property article The Article object to be saved or deleted.
     */
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    /**
     * Event to remove a side effect message.
     *
     * This event is triggered to clear a side effect message, such as a
     * "Saved" or "Deleted" toast, that was previously displayed to the user.
     * It is used to ensure that the message is only shown once and then
     * cleared from the UI state.
     */
    data object RemoveSideEffect : DetailsEvent()
}