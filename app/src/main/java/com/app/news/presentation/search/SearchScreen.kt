package com.app.news.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.news.core.presentation.ArticlesList
import com.app.news.core.presentation.SearchBar
import com.app.news.core.presentation.dummyArticle
import com.app.news.presentation.Dimens.ExtraSmallPadding2
import com.app.news.presentation.Dimens.MediumPadding1
import com.app.news.presentation.navgraph.Route
import com.app.news.presentation.theme.NewsAppTheme
import kotlinx.coroutines.flow.flowOf

/**
 * Search screen composable.
 *
 * Displays a search bar and a list of articles based on the search query.
 *
 * @param state The current state of the search screen.
 * @param event Callback for search events.
 * @param navigate Callback for navigation.
 * @param modifier Modifier for styling.
 */
@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Main content column.
    Column(
        modifier = modifier
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1) // Add padding.
            .statusBarsPadding() // Add padding for the status bar.
            .navigationBarsPadding() // Add padding for the navigation bar.
            .fillMaxSize() // Fill the available space.
    ) {
        // Search bar.
        SearchBar(
            text = state.searchQuery, // Set the search query text.
            readOnly = false, // Allow text input.
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) }, // Update the search query.
            onSearch = { event(SearchEvent.SearchNews) } // Trigger the search.
        )

        // Spacer below search bar.
        Spacer(modifier = Modifier.padding(ExtraSmallPadding2)) // Add a small space.

        // Article list.
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems() // Collect articles as lazy paging items.
            ArticlesList(
                articles = articles, // Display the articles.
                onClick = { navigate(Route.DetailsScreen.route) } // Navigate to details screen on click.
            )
        }
    }
}

/**
 * Preview function for the SearchScreen composable.
 *
 * This function is used to preview the SearchScreen composable in the IDE.
 * It sets up a dummy SearchState and uses a flow of dummy articles for the preview.
 */
@PreviewLightDark
@Composable
fun SearchScreenPreview() {
    NewsAppTheme {
        SearchScreen(
            state = SearchState(
                searchQuery = "Example Search",
                articles = flowOf(
                    PagingData.from(
                        listOf(
                            dummyArticle,
                            dummyArticle,
                            dummyArticle
                        )
                    )
                )
            ),
            event = {},
            navigate = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}