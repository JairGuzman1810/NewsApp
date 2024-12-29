package com.app.news.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.news.domain.model.Article
import com.app.news.presentation.Dimens.ExtraSmallPadding
import com.app.news.presentation.Dimens.MediumPadding1
import com.app.news.presentation.theme.NewsAppTheme
import kotlinx.coroutines.flow.flowOf

/**
 * Composable function that displays a list of articles using a LazyColumn.
 *
 * This function takes a list of articles wrapped in LazyPagingItems and
 * displays them in a scrollable list. It also handles different loading
 * states and displays appropriate UI elements (like a shimmer effect or
 * an empty screen) when necessary.
 *
 * @param modifier Modifier for styling the ArticlesList.
 * @param articles LazyPagingItems containing the list of articles to display.
 * @param onClick Callback function to be executed when an article is clicked.
 */
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    // Determine if the list should be displayed based on the paging result.
    val handlePagingResult = handlePagingResult(articles = articles)

    // If the paging result is successful, display the list.
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(), // Fill the available space.
            verticalArrangement = Arrangement.spacedBy(MediumPadding1), // Add space between items.
            contentPadding = PaddingValues(all = ExtraSmallPadding) // Add padding around the list.
        ) {
            // Display each article in the list.
            items(count = articles.itemCount) { index ->
                // Get the article at the current index.
                articles[index]?.let { article ->
                    // Display the ArticleCard for the current article.
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }
        }
    }
}

/**
 * Composable function that handles the different loading states of the Paging library.
 *
 * This function checks the current load state of the LazyPagingItems and
 * displays the appropriate UI element based on the state.
 *
 * @param articles LazyPagingItems containing the list of articles.
 * @return Boolean indicating whether the list should be displayed (true) or not (false).
 */
@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
): Boolean {
    // Get the current load state of the articles.
    val loadState = articles.loadState

    // Determine if there's an error in any of the load states.
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    // Handle different load states.
    return when {
        // If the list is currently refreshing, show the shimmer effect.
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false // Don't display the list yet.
        }
        // If there's an error, show the empty screen.
        error != null -> {
            EmptyScreen()
            false // Don't display the list.
        }
        // Otherwise, the list is ready to be displayed.
        else -> {
            true // Display the list.
        }
    }
}

/**
 * Composable function that displays a shimmer effect for loading articles.
 *
 * This function displays a list of ArticleCardShimmerEffect components to
 * indicate that the articles are currently loading.
 */
@Composable
private fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MediumPadding1), // Add space between shimmer items.
    ) {
        // Repeat the shimmer effect 10 times.
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier
                    .padding(horizontal = MediumPadding1) // Add horizontal padding.
            )
        }
    }
}

/**
 * A preview composable for the ArticlesList component.
 *
 * This composable allows you to preview the ArticlesList directly within
 * Android Studio. It demonstrates the list with dummy articles and an
 * empty click action.
 */
@PreviewLightDark
@Composable
fun ArticlesListPreview() {
    NewsAppTheme {
        ArticlesList(
            articles = flowOf(
                PagingData.from(
                    listOf(
                        dummyArticle,
                        dummyArticle.copy(title = "Article 2"),
                        dummyArticle.copy(title = "Article 3"),
                        dummyArticle.copy(title = "Article 4"),
                        dummyArticle.copy(title = "Article 5"),
                        dummyArticle.copy(title = "Article 6"),
                        dummyArticle.copy(title = "Article 7"),
                        dummyArticle.copy(title = "Article 8"),
                        dummyArticle.copy(title = "Article 9"),
                        dummyArticle.copy(title = "Article 10"),
                    ),
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.NotLoading(endOfPaginationReached = false),
                        prepend = LoadState.NotLoading(endOfPaginationReached = false),
                        append = LoadState.NotLoading(endOfPaginationReached = false)
                    )
                )
            ).collectAsLazyPagingItems(),
            onClick = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}