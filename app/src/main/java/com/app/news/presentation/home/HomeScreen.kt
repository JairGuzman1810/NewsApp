package com.app.news.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.news.R
import com.app.news.core.presentation.ArticlesList
import com.app.news.core.presentation.SearchBar
import com.app.news.core.presentation.dummyArticle
import com.app.news.domain.model.Article
import com.app.news.presentation.Dimens.ExtraSmallPadding2
import com.app.news.presentation.Dimens.MediumPadding1
import com.app.news.presentation.navgraph.Route
import com.app.news.presentation.theme.NewsAppTheme
import kotlinx.coroutines.flow.flowOf

/**
 * Composable function that displays the home screen of the news application.
 *
 * This function displays the application logo, a search bar, a scrolling
 * list of article titles, and a list of articles.
 *
 * @param articles LazyPagingItems containing the list of articles to display.
 * @param navigate Callback function to navigate to different screens.
 * @param modifier Modifier for styling the HomeScreen.
 */
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Create a derived state of the article titles to display in the marquee.
    val titles by remember {
        derivedStateOf {
            // If there are more than 10 articles, get the first 10 titles and join them with a separator.
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(0..9) // Get the first 10 articles.
                    .joinToString(separator = " \uD83d\uDFE5 ") { it.title } // Join the titles with a separator.
            } else {
                "" // Otherwise, return an empty string.
            }
        }
    }

    // Main column for the home screen content.
    Column(
        modifier = modifier
            .fillMaxSize() // Fill the available space.
            .padding(top = MediumPadding1) // Add padding at the top.
            .statusBarsPadding() // Add padding for the status bar.
    ) {
        // Display the application logo.
        Image(
            painter = painterResource(id = R.drawable.ic_logo), // Load the logo image.
            contentDescription = null, // No content description needed for decorative images.
            modifier = Modifier
                .width(150.dp) // Set the width of the logo.
                .height(30.dp) // Set the height of the logo.
                .padding(horizontal = MediumPadding1) // Add horizontal padding.
        )

        // Add a spacer.
        Spacer(modifier = Modifier.height(MediumPadding1))

        // Display the search bar.
        SearchBar(
            modifier = Modifier
                .padding(horizontal = MediumPadding1), // Add horizontal padding.
            text = "", // Initial text is empty.
            readOnly = true, // The search bar is read-only.
            onValueChange = {}, // No action on text change.
            onSearch = {}, // No action on search.
            onClick = {
                navigate(Route.SearchScreen.route) // Navigate to the search screen when clicked.
            }
        )

        // Add a spacer.
        Spacer(modifier = Modifier.height(MediumPadding1))

        // Display the scrolling article titles.
        Text(
            text = titles, // The titles to display.
            modifier = Modifier
                .fillMaxWidth() // Fill the available width.
                .padding(horizontal = MediumPadding1) // Add horizontal padding.
                .basicMarquee(), // Apply the marquee effect.
            fontSize = 12.sp, // Set the font size.
            color = colorResource(id = R.color.placeholder) // Set the text color.
        )

        // Add a spacer.
        Spacer(modifier = Modifier.height(MediumPadding1))

        // Display the list of articles.
        ArticlesList(
            modifier = Modifier.padding(horizontal = ExtraSmallPadding2), // Add horizontal padding.
            articles = articles, // The articles to display.
            onClick = { navigate(Route.DetailsScreen.route) } // Navigate to the details screen when an article is clicked.
        )
    }
}


/**
 * A preview composable for the HomeScreen component.
 *
 * This composable allows you to preview the HomeScreen directly within
 * Android Studio. It demonstrates the screen with dummy articles and an
 * empty navigation action.
 */
@Preview
@Composable
fun HomeScreenPreview() {
    NewsAppTheme {
        HomeScreen(
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
                        dummyArticle.copy(title = "Article 11"),
                    ),
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.NotLoading(endOfPaginationReached = false),
                        prepend = LoadState.NotLoading(endOfPaginationReached = false),
                        append = LoadState.NotLoading(endOfPaginationReached = false)
                    )
                )
            ).collectAsLazyPagingItems(),
            navigate = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}