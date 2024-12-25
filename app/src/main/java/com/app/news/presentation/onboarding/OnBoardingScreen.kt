package com.app.news.presentation.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.news.presentation.onboarding.components.OnBoardingPage
import com.app.news.ui.theme.NewsAppTheme

/**
 * The main composable for the onboarding screen.
 *
 * This screen displays a series of pages to introduce the user to the app's
 * features. It uses a HorizontalPager to allow the user to swipe through
 * the pages.
 *
 * @param modifier The modifier to be applied to the layout.
 */
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier
) {

    // A column to hold the pager and other UI elements.
    Column(
        modifier = modifier
            .fillMaxSize() // Make the column fill the available space.
    ) {

        // State to keep track of the current page in the pager.
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size // The total number of pages.
        }

        // State to determine the text for the buttons based on the current page.
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) { // Check the current page.
                    0 -> listOf("", "Next") // On the first page, show only "Next".
                    1 -> listOf("Back", "Next") // On the second page, show "Back" and "Next".
                    2 -> listOf("Back", "Get Started") // On the last page, show "Back" and "Get Started".
                    else -> listOf("", "") // For any other page, show nothing.
                }
            }
        }

        // A horizontal pager to display the onboarding pages.
        HorizontalPager(
            state = pagerState // The state to control the pager.
        ) { index -> // The index of the current page.
            OnBoardingPage(page = pages[index]) // Display the page content.
        }

    }
}

/**
 * A preview composable for the OnBoardingScreen component.
 *
 * This composable allows you to preview the OnBoardingScreen directly within
 * Android Studio.
 */
@Preview
@Composable
private fun OnBoardingScreenPreview() {
    NewsAppTheme {
        OnBoardingScreen()
    }
}