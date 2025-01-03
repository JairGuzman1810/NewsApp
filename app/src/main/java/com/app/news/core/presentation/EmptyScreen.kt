package com.app.news.core.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.app.news.R
import com.app.news.presentation.theme.NewsAppTheme
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Composable function that displays an empty screen with a relevant message and icon.
 *
 * This function is used to display a screen when there are no articles to show,
 * when an error occurs, or when a custom message needs to be displayed.
 * It can display different messages and icons based on the provided:
 * - `LoadState.Error`: For displaying error-related messages.
 * - `customMessage`: For displaying a specific message when no error is present.
 *
 * @param error Optional LoadState.Error object to display a specific error message.
 * @param customMessage Optional custom message to display when there is no error.
 */
@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    customMessage: String? = null,
) {
    // Determine the message to display based on the error.
    var message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }

    // Determine the icon to display based on the error.
    var icon by remember {
        mutableIntStateOf(R.drawable.ic_network_error) // Default icon for network errors.
    }

    // If there's no error, display a default message and icon.
    if (error == null) {
        message = customMessage ?: "You have not saved news so far !" // Use custom message if provided, else default.
        icon = R.drawable.ic_search_document // Default icon when there's no error.
    }

    // State to control the start of the animation.
    var startAnimation by remember {
        mutableStateOf(false)
    }

    // Animation for the alpha value of the icon and text.
    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f, // Target alpha value.
        animationSpec = tween(durationMillis = 1500), // Animation duration.
        label = "EmptyAnimation"
    )

    // Start the animation when the composable is launched.
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    // Display the empty content with the determined message, icon, and animation.
    EmptyContent(alphaAnim = alphaAnimation, message = message, iconId = icon)
}

/**
 * Composable function that displays the content of the empty screen.
 *
 * This function displays an icon and a message in the center of the screen.
 * It uses an alpha animation to fade in the icon and text.
 *
 * @param alphaAnim The alpha value for the animation.
 * @param message The message to display.
 * @param iconId The resource ID of the icon to display.
 * @param modifier Modifier for styling the EmptyContent.
 */
@Composable
fun EmptyContent(
    alphaAnim: Float,
    message: String,
    iconId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(), // Fill the available space.
        horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally.
        verticalArrangement = Arrangement.Center // Center items vertically.
    ) {
        // Display the icon.
        Icon(
            painter = painterResource(id = iconId), // Load the icon from the resource ID.
            contentDescription = null, // No content description needed for decorative icons.
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray, // Set the icon color based on the theme.
            modifier = Modifier
                .size(120.dp) // Set the icon size.
                .alpha(alphaAnim) // Apply the alpha animation.
        )
        // Display the message.
        Text(
            modifier = Modifier
                .padding(10.dp) // Add padding around the text.
                .alpha(alphaAnim), // Apply the alpha animation.
            text = message, // Set the text.
            style = MaterialTheme.typography.bodyMedium, // Set the text style.
            color = if (isSystemInDarkTheme()) LightGray else DarkGray, // Set the text color based on the theme.
        )
    }
}

/**
 * Function that parses the error message from a LoadState.Error object.
 *
 * This function returns a user-friendly error message based on the type of error.
 *
 * @param error Optional LoadState.Error object to parse the error message from.
 * @return A user-friendly error message.
 */
fun parseErrorMessage(error: LoadState.Error?): String {
    return when (error?.error) {
        // If the error is a SocketTimeoutException, return a "Server Unavailable" message.
        is SocketTimeoutException -> {
            "Server Unavailable."
        }
        // If the error is a ConnectException, return an "Internet Unavailable" message.
        is ConnectException -> {
            "Internet Unavailable."
        }
        // For any other error, return a generic "Unknown Error" message.
        else -> {
            "Unknown Error."
        }
    }
}

/**
 * Preview function for the EmptyScreen composable.
 *
 * This composable allows you to preview the EmptyScreen directly within
 * Android Studio. It demonstrates the screen with a empty screen and the error.
 */
@PreviewLightDark
@Composable
private fun EmptyScreenPreview() {
    NewsAppTheme {
        EmptyContent(
            alphaAnim = 0.3f,
            message = "Internet Unavailable.",
            iconId = R.drawable.ic_network_error,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background) // Set the background color.
        )
    }
}