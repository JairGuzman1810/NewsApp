package com.app.news.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.app.news.R
import com.app.news.presentation.theme.NewsAppTheme

/**
 * Composable function that displays the top app bar for the details screen.
 *
 * This function displays a back button, a bookmark button, a share button, and a button to open in browser.
 *
 * @param onBrowsingClick Callback for when the browsing button is clicked.
 * @param onShareClick Callback for when the share button is clicked.
 * @param onBookmarkClick Callback for when the bookmark button is clicked.
 * @param onBackClick Callback for when the back button is clicked.
 * @param modifier Modifier for styling the DetailsTopBar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TopAppBar composable for the details screen.
    TopAppBar(
        title = {}, // No title is displayed in this TopAppBar.
        modifier = modifier.fillMaxWidth(), // Fill the available width.
        colors = TopAppBarDefaults.mediumTopAppBarColors( // Set the colors for the TopAppBar.
            containerColor = Color.Transparent, // Make the background transparent.
            actionIconContentColor = colorResource(id = R.color.body), // Set the color for action icons.
            navigationIconContentColor = colorResource(id = R.color.body) // Set the color for the navigation icon.
        ),
        navigationIcon = { // Navigation icon (back button).
            IconButton(onClick = onBackClick) { // Handle the back button click.
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow), // Load the back arrow icon.
                    contentDescription = null // No content description needed.
                )
            }
        },
        actions = { // Action buttons.
            IconButton(onClick = onBookmarkClick) { // Handle the bookmark button click.
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark), // Load the bookmark icon.
                    contentDescription = null // No content description needed.
                )
            }

            IconButton(onClick = onShareClick) { // Handle the share button click.
                Icon(
                    imageVector = Icons.Default.Share, // Load the share icon.
                    contentDescription = null // No content description needed.
                )
            }

            IconButton(onClick = onBrowsingClick) { // Handle the browsing button click.
                Icon(
                    painter = painterResource(id = R.drawable.ic_network), // Load the network icon.
                    contentDescription = null // No content description needed.
                )
            }
        }
    )
}

/**
 * Preview composable for the DetailsTopBar component.
 *
 * This composable allows you to preview the DetailsTopBar directly within
 * Android Studio. It demonstrates the top bar with empty click actions.
 */
@PreviewLightDark
@Composable
private fun DetailsTopBarPreview() {
    NewsAppTheme {
        DetailsTopBar(
            onBrowsingClick = {}, // Empty click action.
            onShareClick = {}, // Empty click action.
            onBookmarkClick = {}, // Empty click action.
            onBackClick = {}, // Empty click action.
            modifier = Modifier.background(MaterialTheme.colorScheme.background) // Set the background color.
        )
    }
}