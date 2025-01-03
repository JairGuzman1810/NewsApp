package com.app.news.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.app.news.R
import com.app.news.presentation.theme.Dimens.IconSize
import com.app.news.presentation.theme.NewsAppTheme

/**
 * Composable function that displays a search bar.
 *
 * This function creates a customizable search bar with a leading icon,
 * placeholder text, and various styling options. It also handles click
 * events and search actions.
 *
 * @param modifier Modifier for styling the SearchBar.
 * @param text The current text in the search bar.
 * @param readOnly Whether the search bar is read-only.
 * @param onClick Callback function to be executed when the search bar is clicked.
 * @param onValueChange Callback function to be executed when the text in the search bar changes.
 * @param onSearch Callback function to be executed when the search action is triggered.
 */
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    // Create a MutableInteractionSource to track interactions with the TextField.
    val interactionSource = remember { MutableInteractionSource() }

    // Collect the pressed state of the interaction source.
    val isClicked = interactionSource.collectIsPressedAsState().value

    // Launch an effect to invoke the onClick callback when the search bar is clicked.
    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }
    }

    // Box to contain the TextField.
    Box(
        modifier = modifier
    ) {
        // TextField for the search bar.
        TextField(
            modifier = Modifier
                .fillMaxWidth() // Fill the available width.
                .searchBarBorder(), // Apply the custom border.
            value = text, // The current text in the search bar.
            onValueChange = onValueChange, // Callback for text changes.
            readOnly = readOnly, // Whether the search bar is read-only.
            leadingIcon = {
                // Leading icon (search icon).
                Icon(
                    modifier = Modifier.size(
                        IconSize // Set the icon size.
                    ),
                    painter = painterResource(id = R.drawable.ic_search), // Load the search icon.
                    contentDescription = null, // No content description needed for decorative icons.
                    tint = colorResource(id = R.color.body) // Set the icon color.
                )
            },
            placeholder = {
                // Placeholder text.
                Text(
                    text = "Search", // Placeholder text.
                    style = MaterialTheme.typography.bodySmall, // Set the text style.
                    color = colorResource(id = R.color.placeholder) // Set the text color.
                )
            },
            shape = MaterialTheme.shapes.medium, // Set the shape of the TextField.
            colors = TextFieldDefaults.colors(
                // Customize the colors of the TextField.
                focusedContainerColor = colorResource(id = R.color.input_background), // Background when focused.
                unfocusedContainerColor = colorResource(id = R.color.input_background), // Background when unfocused.
                disabledContainerColor = colorResource(id = R.color.input_background), // Background when disabled.
                focusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black, // Text color when focused.
                unfocusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black, // Text color when unfocused.
                disabledTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black, // Text color when disabled.
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black, // Cursor color.
                focusedIndicatorColor = Color.Transparent, // Indicator color when focused.
                unfocusedIndicatorColor = Color.Transparent, // Indicator color when unfocused.
                disabledIndicatorColor = Color.Transparent, // Indicator color when disabled.
                errorIndicatorColor = Color.Transparent, // Indicator color when error.
            ),
            singleLine = true, // Ensure the text is on a single line.
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), // Set the keyboard action to "Search".
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() } // Callback for the search action.
            ),
            textStyle = MaterialTheme.typography.bodySmall, // Set the text style.
            interactionSource = interactionSource // Set the interaction source.
        )
    }
}

/**
 * Modifier function to add a border to the search bar.
 *
 * This function adds a border to the search bar only when the system is in light mode.
 *
 * @return A Modifier with the border applied.
 */
fun Modifier.searchBarBorder() = composed {
    // Add a border only in light mode.
    if (!isSystemInDarkTheme()) {
        border(
            width = 1.dp, // Border width.
            color = Color.Black, // Border color.
            shape = MaterialTheme.shapes.medium // Border shape.
        )
    } else {
        this // Return the original modifier in dark mode.
    }
}

/**
 * Preview function for the SearchBar composable.
 *
 * This function is used to preview the SearchBar composable in the IDE.
 */
@PreviewLightDark
@Composable
private fun SearchBarPreview() {
    NewsAppTheme {
        SearchBar(
            text = "News",
            readOnly = false,
            onValueChange = {},
            onSearch = {},
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background) // Set the background color.
        )
    }
}