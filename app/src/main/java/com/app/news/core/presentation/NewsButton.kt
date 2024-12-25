package com.app.news.core.presentation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.news.ui.theme.NewsAppTheme

/**
 * A custom button composable used throughout the application.
 *
 * This button provides a consistent look and feel, adhering to the app's
 * Material Design theme. It's designed to be reused across different screens
 * and features.
 *
 * @param text The text to be displayed on the button.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param modifier The modifier to be applied to the button.
 */
@Composable
fun NewsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    // The main Button composable from Material 3.
    Button(
        onClick = onClick, // The action to perform when the button is clicked.
        colors = ButtonDefaults.buttonColors( // Customize the button's colors.
            containerColor = MaterialTheme.colorScheme.primary, // Set the background color to the primary color from the theme.
            contentColor = Color.White // Set the text color to white.
        ),
        shape = RoundedCornerShape(size = 6.dp), // Give the button rounded corners.
        modifier = modifier, // Apply any external modifiers to the button.
    ) {
        // The text displayed inside the button.
        Text(
            text = text, // Set the text to the provided text parameter.
            style = MaterialTheme.typography.labelMedium.copy( // Customize the text style.
                fontWeight = FontWeight.SemiBold // Make the text semi-bold.
            )
        )
    }
}

/**
 * A preview composable for the NewsButton component.
 *
 * This composable allows you to preview the NewsButton directly within
 * Android Studio. It demonstrates the button with the text "Button" and an
 * empty click action.
 */
@Preview
@Composable
private fun NewsButtonPreview() {
    NewsAppTheme {
        NewsButton(
            text = "Button", // The text to be displayed on the button.
            onClick = {} // An empty lambda for the click action.
        )
    }
}