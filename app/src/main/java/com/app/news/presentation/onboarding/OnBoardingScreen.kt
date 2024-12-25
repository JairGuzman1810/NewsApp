package com.app.news.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.news.core.presentation.NewsButton
import com.app.news.core.presentation.NewsTextButton
import com.app.news.presentation.Dimens.MediumPadding2
import com.app.news.presentation.Dimens.PageIndicatorWidth
import com.app.news.presentation.onboarding.components.OnBoardingPage
import com.app.news.presentation.onboarding.components.PageIndicator
import com.app.news.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

/**
 * The main composable for the onboarding screen.
 *
 * This screen displays a series of pages to introduce the user to the app's
 * features. It uses a HorizontalPager to allow the user to swipe through
 * the pages. It also includes navigation buttons and a page indicator.
 *
 * @param modifier The modifier to be applied to the layout.
 */
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier
) {

    // A column to hold the pager, page indicator, and navigation buttons.
    Column(
        modifier = modifier
            .fillMaxSize() // Make the column fill the available space.
    ) {

        // State to keep track of the current page in the pager.
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size // The total number of pages.
        }

        // State to determine the text for the navigation buttons based on the current page.
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) { // Check the current page.
                    0 -> listOf("", "Next") // On the first page, show only "Next".
                    1 -> listOf("Back", "Next") // On the second page, show "Back" and "Next".
                    2 -> listOf(
                        "Back",
                        "Get Started"
                    ) // On the last page, show "Back" and "Get Started".
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

        // Add a flexible space that will push the buttons to the bottom.
        Spacer(modifier = Modifier.weight(1f))

        // A row to hold the page indicator and the navigation buttons.
        Row(
            modifier = Modifier
                .fillMaxWidth() // Make the row fill the available width.
                .padding(horizontal = MediumPadding2) // Add horizontal padding.
                .navigationBarsPadding(), // Add padding for the navigation bars.
            horizontalArrangement = Arrangement.SpaceBetween, // Distribute the elements evenly.
            verticalAlignment = Alignment.CenterVertically // Center the elements vertically.
        ) {

            // Display the page indicator.
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth), // Set the width of the indicator.
                pageSize = pages.size, // The total number of pages.
                selectedPage = pagerState.currentPage, // The currently selected page.
            )

            // A row to hold the navigation buttons.
            Row(
                verticalAlignment = Alignment.CenterVertically // Center the buttons vertically.
            ) {

                // Create a coroutine scope to launch coroutines.
                val scope = rememberCoroutineScope()

                // Display the "Back" button if it's needed.
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0], // Set the text to the first element of the button state.
                        onClick = { // The action to perform when the button is clicked.
                            scope.launch { // Launch a coroutine.
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1) // Go to the previous page.
                            }
                        }
                    )
                }

                // Display the "Next" or "Get Started" button.
                NewsButton(
                    text = buttonState.value[1], // Set the text to the second element of the button state.
                    onClick = { // The action to perform when the button is clicked.
                        scope.launch { // Launch a coroutine.
                            if (pagerState.currentPage == pages.lastIndex) {
                                // If on the last page, navigate to the home screen.
                                // TODO: Navigate to the home screen.
                            } else {
                                pagerState.animateScrollToPage( // Go to the next page.
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
            }
        }
        // Add a flexible space to separate the buttons from the bottom.
        Spacer(modifier = Modifier.weight(0.5f))
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