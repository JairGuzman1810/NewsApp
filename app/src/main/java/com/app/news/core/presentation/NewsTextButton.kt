package com.app.news.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.news.presentation.theme.NewsAppTheme
import com.app.news.presentation.theme.WhiteGray

/**
 * A custom text button composable used throughout the application.
 *
 * This button provides a consistent look and feel, adhering to the app's
 * Material Design theme. It's designed to be reused across different screens
 * and features. This is an alternative to NewsButton when a text-only
 * button is desired.
 *
 * @param text The text to be displayed on the button.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param modifier The modifier to be applied to the button.
 */
@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    // The main TextButton composable from Material 3.
    TextButton(
        onClick = onClick, // The action to perform when the button is clicked.
        modifier = modifier // Apply any external modifiers to the button.
    ) {
        // The text displayed inside the button.
        Text(
            text = text, // Set the text to the provided text parameter.
            style = MaterialTheme.typography.labelMedium.copy( // Customize the text style.
                fontWeight = FontWeight.SemiBold // Make the text semi-bold.
            ),
            color = WhiteGray // Set the text color to WhiteGray.
        )
    }
}

/**
 * A preview composable for the NewsTextButton component.
 *
 * This composable allows you to preview the NewsTextButton directly within
 * Android Studio. It demonstrates the button with the text "Back" and an
 * empty click action.
 */
@Preview
@Composable
private fun NewsTextButtonPreview() {
    NewsAppTheme {
        NewsTextButton(
            text = "Back", // The text to be displayed on the button.
            onClick = {} // An empty lambda for the click action.
        )
    }
}