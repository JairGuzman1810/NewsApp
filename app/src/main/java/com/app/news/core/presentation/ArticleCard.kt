package com.app.news.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.news.R
import com.app.news.domain.model.Article
import com.app.news.domain.model.Source
import com.app.news.presentation.theme.Dimens.ArticleCardSize
import com.app.news.presentation.theme.Dimens.ExtraSmallPadding
import com.app.news.presentation.theme.Dimens.ExtraSmallPadding2
import com.app.news.presentation.theme.Dimens.MediumPadding1
import com.app.news.presentation.theme.Dimens.SmallIconSize
import com.app.news.presentation.theme.NewsAppTheme

/**
 * A composable function that displays an article card.
 *
 * This card shows the article's image, source, title, and published time.
 * It's designed to be used in a list or grid of articles.
 *
 * @param modifier The modifier to apply to the card.
 * @param article The article data to display.
 * @param onClick Callback function to be executed when the card is clicked.
 */
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    // Get the current context.
    val context = LocalContext.current
    // Main container for the article card.
    Row(
        modifier = modifier
            .clickable { onClick() } // Make the card clickable.
    ) {
        // Display the article image.
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize) // Set the image size.
                .clip(MaterialTheme.shapes.medium), // Clip the image to a rounded shape.
            model = ImageRequest.Builder(context = context).data(article.urlToImage).build(), // Load the image from the URL.
            contentDescription = null, // No content description needed for images.
            contentScale = ContentScale.Crop // Crop the image to fit the size.
        )
        // Column for the article's text content.
        Column(
            verticalArrangement = Arrangement.SpaceAround, // Distribute space evenly.
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding) // Add horizontal padding.
                .height(ArticleCardSize) // Set the height of the column.
        ) {
            // Display the article title.
            Text(
                text = article.title, // Display the article title.
                style = MaterialTheme.typography.bodyMedium, // Apply text style.
                color = colorResource(id = R.color.text_title), // Set text color.
                maxLines = 2, // Limit the number of lines.
                overflow = TextOverflow.Ellipsis // Add ellipsis if the text overflows.
            )
            // Row for the article's source, time icon, and published time.
            Row(
                verticalAlignment = Alignment.CenterVertically, // Center items vertically.
            ) {
                // Display the article source.
                Text(
                    text = article.source.name, // Display the source name.
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold // Make the source name bold.
                    ),
                    color = colorResource(id = R.color.body), // Set text color.
                )
                // Add a small space between the source and the time icon.
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                // Display the time icon.
                Icon(
                    painter = painterResource(id = R.drawable.ic_time), // Load the time icon.
                    contentDescription = null, // No content description needed for icons.
                    modifier = Modifier.size(SmallIconSize), // Set the icon size.
                    tint = colorResource(id = R.color.body) // Set the icon color.
                )
                // Add a small space between the time icon and the published time.
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                // Display the published time.
                Text(
                    text = article.publishedAt, // Display the published time.
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold // Make the published time bold.
                    ),
                    color = colorResource(id = R.color.body), // Set text color.
                )
            }
        }
    }
}

/**
 * Dummy data for previewing the ArticleCard.
 *
 * This data is used to display a sample article in the preview.
 */
val dummyArticle = Article(
    author = "John Doe",
    content = "This is the content of the article.",
    description = "This is a short description of the article.",
    publishedAt = "2023-10-27T10:00:00Z",
    source = Source(id = "bbc-news", name = "BBC News"),
    title = "Breaking News: Major Event Unfolds",
    url = "https://www.example.com/article",
    urlToImage = "https://a.fsdn.com/sd/topics/bitcoin_64.png"
)

/**
 * A preview composable for the ArticleCard component.
 *
 * This composable allows you to preview the ArticleCard directly within
 * Android Studio. It demonstrates the card with the dummyArticle data and an
 * empty click action.
 */
@PreviewLightDark
@Composable
private fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(
            article = dummyArticle,
            onClick = {}, // Empty lambda for the preview.
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background) // Set the background color.
        )
    }
}

/**
 * Composable function that displays a shimmer effect for the ArticleCard.
 *
 * This function is used to show a loading state for the ArticleCard.
 * It displays a shimmer effect on the image, title, and source/time
 * sections of the card.
 *
 * @param modifier The modifier to apply to the shimmer effect.
 */
@Composable
fun ArticleCardShimmerEffect(modifier: Modifier = Modifier) {
    // Main container for the shimmer effect.
    Row(
        modifier = modifier // Apply the modifier to the row.
    ) {
        // Box for the image shimmer effect.
        Box(
            modifier = Modifier
                .size(ArticleCardSize) // Set the image size.
                .clip(MaterialTheme.shapes.medium) // Clip the image to a rounded shape.
                .shimmerEffect() // Apply the shimmer effect.
        )

        // Column for the text shimmer effects.
        Column(
            verticalArrangement = Arrangement.SpaceAround, // Distribute space evenly.
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding) // Add horizontal padding.
                .height(ArticleCardSize) // Set the height of the column.
        ) {
            // Box for the title shimmer effect.
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Fill the available width.
                    .height(30.dp) // Set the height of the box.
                    .padding(horizontal = MediumPadding1) // Add horizontal padding.
                    .shimmerEffect() // Apply the shimmer effect.
            )
            // Row for the source/time shimmer effect.
            Row(
                verticalAlignment = Alignment.CenterVertically // Center items vertically.
            ) {
                // Box for the source/time shimmer effect.
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f) // Fill half of the available width.
                        .padding(horizontal = MediumPadding1) // Add horizontal padding.
                        .height(15.dp) // Set the height of the box.
                        .shimmerEffect() // Apply the shimmer effect.
                )
            }
        }
    }
}

/**
 * A preview composable for the ArticleCardShimmerEffect component.
 *
 * This composable allows you to preview the ArticleCardShimmerEffect directly
 * within Android Studio. It demonstrates the shimmer effect as it would appear
 * during a loading state.
 */
@PreviewLightDark
@Composable
private fun ArticleCardShimmerEffectPreview() {
    NewsAppTheme {
        ArticleCardShimmerEffect()
    }
}