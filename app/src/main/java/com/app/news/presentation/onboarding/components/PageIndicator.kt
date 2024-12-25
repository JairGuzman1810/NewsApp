package com.app.news.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.app.news.presentation.Dimens.IndicatorSize
import com.app.news.presentation.onboarding.pages
import com.app.news.presentation.theme.BlueGray
import com.app.news.presentation.theme.NewsAppTheme
/**
 * A composable function that displays a row of indicators representing the current page
 * in a multi-page layout, such as an onboarding flow.
 *
 * This component is responsible for showing the user their progress through a series of
 * pages. The currently selected page is highlighted with a different color.
 *
 * @param pageSize The total number of pages.
 * @param selectedPage The index of the currently selected page (0-based).
 * @param selectedColor The color of the indicator for the selected page. Defaults to the
 *                      primary color from the Material theme.
 * @param unselectedColor The color of the indicators for the unselected pages. Defaults to
 *                        BlueGray.
 * @param modifier The modifier to be applied to the layout.
 */
@Composable
fun PageIndicator(
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = BlueGray,
    modifier: Modifier = Modifier
) {

    // A row to hold the page indicators.
    Row(
        modifier = modifier, // Apply any external modifiers to the row.
        horizontalArrangement = Arrangement.SpaceBetween // Distribute the indicators evenly.
    ) {
        // Repeat for each page.
        repeat(pageSize) { page ->
            // A box to represent a single page indicator.
            Box(
                modifier = Modifier
                    .size(IndicatorSize) // Set the size of the indicator.
                    .clip(CircleShape) // Make the indicator a circle.
                    .background( // Set the background color of the indicator.
                        color = if (page == selectedPage) selectedColor else unselectedColor // If its the selected page, use the selected color, else the unselected color.
                    )
            )
        }
    }
}

/**
 * A preview composable for the PageIndicator component.
 *
 * This composable allows you to preview the PageIndicator directly within
 * Android Studio. It demonstrates the indicator with the number of pages
 * defined in the pages list and the first page selected.
 */
@Preview
@Composable
private fun PageIndicatorPreview() {
    NewsAppTheme {
        PageIndicator(
            pageSize = pages.size, // The total number of pages.
            selectedPage = 0, // The index of the currently selected page.
            modifier = Modifier
                .background(Color.White) // Set the background color for the preview.
        )
    }
}