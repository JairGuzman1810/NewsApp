package com.app.news.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil.compose.AsyncImage
import com.app.news.R
import com.app.news.core.presentation.dummyArticle
import com.app.news.domain.model.Article
import com.app.news.presentation.Dimens.ArticleImageHeight
import com.app.news.presentation.Dimens.MediumPadding1
import com.app.news.presentation.details.components.DetailsTopBar
import com.app.news.presentation.theme.NewsAppTheme

/**
 * Composable function that displays the details screen for a news article.
 *
 * This function displays the article's image, title, and content, along with
 * a top app bar that includes actions for browsing, sharing, bookmarking, and going back.
 *
 * @param article The article to display.
 * @param event Callback for details screen events.
 * @param navigateUp Callback to navigate up in the navigation hierarchy.
 * @param modifier Modifier for styling the DetailsScreen.
 */
@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Get the current context.
    val context = LocalContext.current

    // Main column for the details screen content.
    Column(
        modifier = modifier
            .fillMaxSize() // Fill the available space.
            .statusBarsPadding() // Add padding for the status bar.
            .navigationBarsPadding() // Add padding for the navigation bar.
    ) {
        // Display the top app bar.
        DetailsTopBar(
            onBrowsingClick = { // Handle browsing click.
                Intent(Intent.ACTION_VIEW).also { // Create an intent to view a URI.
                    it.data = Uri.parse(article.url) // Set the URI to the article's URL.
                    if (it.resolveActivity(context.packageManager) != null) { // Check if there's an app to handle the intent.
                        context.startActivity(it) // Start the activity.
                    }
                }
            },
            onShareClick = { // Handle share click.
                Intent(Intent.ACTION_SEND).also { // Create an intent to send data.
                    it.putExtra(Intent.EXTRA_TEXT, article.url) // Put the article's URL as extra text.
                    it.type = "text/plain" // Set the type to plain text.
                    if (it.resolveActivity(context.packageManager) != null) { // Check if there's an app to handle the intent.
                        context.startActivity(it) // Start the activity.
                    }
                }
            },
            onBookmarkClick = { // Handle bookmark click.
                event(DetailsEvent.UpsertDeleteArticle(article)) // Trigger the save/delete article event.
            },
            onBackClick = navigateUp // Handle back click.
        )

        // LazyColumn for the article content.
        LazyColumn(
            modifier = Modifier.fillMaxWidth(), // Fill the available width.
            contentPadding = PaddingValues( // Add padding to the content.
                start = MediumPadding1, // Start padding.
                end = MediumPadding1, // End padding.
                top = MediumPadding1 // Top padding.
            )
        ) {
            item { // Single item for the article details.
                AsyncImage(
                    model = article.urlToImage, // Load the image from the URL.
                    contentDescription = null, // No content description needed.
                    modifier = Modifier
                        .fillMaxWidth() // Fill the available width.
                        .height(ArticleImageHeight) // Set the image height.
                        .clip(MaterialTheme.shapes.medium), // Clip the image with rounded corners.
                    contentScale = ContentScale.Crop // Crop the image to fit.
                )

                Spacer(modifier = Modifier.height(MediumPadding1)) // Add a spacer.

                Text(
                    text = article.title, // Display the article title.
                    style = MaterialTheme.typography.displaySmall, // Set the text style.
                    color = colorResource(id = R.color.text_title) // Set the text color.
                )

                Text(
                    text = article.content, // Display the article content.
                    style = MaterialTheme.typography.bodyMedium, // Set the text style.
                    color = colorResource(id = R.color.body) // Set the text color.
                )
            }
        }
    }
}

/**
 * Preview composable for the DetailsScreen component.
 *
 * This composable allows you to preview the DetailsScreen directly within
 * Android Studio. It demonstrates the screen with a dummy article and empty
 * event and navigation actions.
 */
@PreviewLightDark
@Composable
private fun DetailsScreenPreview() {
    NewsAppTheme {
        DetailsScreen(
            article = dummyArticle, // Use a dummy article for the preview.
            event = {}, // Empty event action.
            navigateUp = {}, // Empty navigation action.
            modifier = Modifier.background(MaterialTheme.colorScheme.background) // Set the background color.
        )
    }
}