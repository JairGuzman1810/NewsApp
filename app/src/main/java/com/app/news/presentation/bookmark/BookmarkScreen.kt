package com.app.news.presentation.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.app.news.R
import com.app.news.core.presentation.ArticlesList
import com.app.news.core.presentation.dummyArticle
import com.app.news.presentation.Dimens.MediumPadding1
import com.app.news.presentation.navgraph.Route
import com.app.news.presentation.theme.NewsAppTheme

/**
 * Composable function for the Bookmark screen.
 *
 * This screen displays a list of bookmarked articles.
 *
 * @param state The current state of the Bookmark screen, containing the list of bookmarked articles.
 * @param navigate Callback function to navigate to another screen.
 * @param modifier Modifier for styling the BookmarkScreen.
 */
@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Main layout for the Bookmark screen.
    Column(
        modifier = modifier
            .fillMaxSize() // Fill the entire screen.
            .statusBarsPadding() // Add padding for the status bar.
            .navigationBarsPadding() // Add padding for the navigation bars.
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1) // Add padding around the content.
    ) {
        // Title text for the Bookmark screen.
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold), // Bold title text.
            color = colorResource(
                id = R.color.text_title // Color for the title.
            )
        )

        // Spacer to add some space between the title and the list.
        Spacer(modifier = Modifier.height(MediumPadding1))

        // Display the list of bookmarked articles.
        ArticlesList(
            articles = state.articles, // List of articles to display.
            onClick = { navigate(Route.DetailsScreen.route) } // Navigate to the details screen when an article is clicked.
        )
    }
}

/**
 * Preview function for the Bookmark screen.
 *
 * This function allows you to preview the Bookmark screen in the IDE.
 */
@PreviewLightDark
@Composable
private fun BookmarkScreenPreview() {
    // Theme for the preview.
    NewsAppTheme {
        // Display the Bookmark screen with dummy data.
        BookmarkScreen(
            state = BookmarkState(
                articles = listOf(
                    dummyArticle,
                    dummyArticle,
                    dummyArticle,
                    dummyArticle
                )
            ),
            navigate = {}, // No navigation in the preview.
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background) // Background color for the preview.
        )
    }
}