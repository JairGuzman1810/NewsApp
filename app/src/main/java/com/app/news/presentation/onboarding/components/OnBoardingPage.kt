package com.app.news.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.app.news.R
import com.app.news.presentation.Dimens.MediumPadding1
import com.app.news.presentation.Dimens.MediumPadding2
import com.app.news.presentation.onboarding.Page
import com.app.news.presentation.onboarding.pages
import com.app.news.presentation.theme.NewsAppTheme

/**
 * A composable function that displays a single page in the onboarding flow.
 *
 * This component is responsible for rendering the content of a single onboarding
 * page, including its image, title, and description. It uses Material Design
 * components for a consistent look and feel.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param page The [Page] object containing the data to be displayed.
 */
@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {

    Column(modifier = modifier) {
        // Display the image for the onboarding page.
        Image(
            modifier = Modifier
                .fillMaxWidth() // Make the image fill the available width.
                .fillMaxWidth(0.6f), // Takes 60% of the available width.
            painter = painterResource(id = page.image), // Load the image from the page object.
            contentDescription = null, // Image is decorative, so no description needed.
            contentScale = ContentScale.Crop // Scales the image to fill the bounds, cropping if necessary.
        )

        // Add a vertical space between the image and the title.
        Spacer(modifier = Modifier.height(MediumPadding1)) // Adds vertical space.

        // Display the title of the onboarding page.
        Text(
            text = page.title, // Set the text to the title from the page object.
            modifier = Modifier
                .padding(horizontal = MediumPadding2), // Adds horizontal padding.
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold // Makes the title bold.
            ),
            color = colorResource(id = R.color.display_small) // Sets the text color.
        )

        // Display the description of the onboarding page.
        Text(
            text = page.description, // Set the text to the description from the page object.
            modifier = Modifier
                .padding(horizontal = MediumPadding2), // Adds horizontal padding.
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold // Makes the description bold.
            ),
            color = colorResource(id = R.color.text_medium) // Sets the text color.
        )
    }

}

/**
 * A preview composable for the OnBoardingPage component.
 *
 * This composable allows you to preview the OnBoardingPage in both light and
 * dark themes directly within Android Studio. It uses the first page from the
 * pages list for demonstration.
 */
@PreviewLightDark
@Composable
private fun OnBoardingPagePreview() {
    NewsAppTheme {
        OnBoardingPage(
            page = pages[0], // Use the first page from the pages list.
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background) // Sets the background color.
        )
    }
}