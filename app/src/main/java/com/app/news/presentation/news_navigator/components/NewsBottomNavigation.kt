package com.app.news.presentation.news_navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.app.news.R
import com.app.news.presentation.theme.Dimens.ExtraSmallPadding2
import com.app.news.presentation.theme.Dimens.IconSize
import com.app.news.presentation.theme.NewsAppTheme

/**
 * Composable function for the bottom navigation bar in the news application.
 *
 * This function creates a bottom navigation bar that allows users to switch
 * between different sections of the app, such as Home, Search, and Bookmark.
 *
 * @param items A list of BottomNavigationItem representing the items in the
 *              navigation bar. Each item contains an icon and a label.
 * @param selectedItem The index of the currently selected item in the
 *                     navigation bar.
 * @param onItemClick Callback function that is invoked when an item in the
 *                    navigation bar is clicked. It receives the index of the
 *                    clicked item.
 * @param modifier Modifier for styling and layout customization of the
 *                 navigation bar.
 */
@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = modifier
            .fillMaxWidth(), // Make the navigation bar fill the width of the screen.
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column( // Column to arrange the icon and text vertically.
                        horizontalAlignment = Alignment.CenterHorizontally // Center the icon and text horizontally.
                    ) {
                        Icon( // Display the icon.
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(IconSize) // Set the size of the icon.
                        )

                        Spacer(modifier = Modifier.size(ExtraSmallPadding2)) // Add a small space between the icon and text.

                        Text( // Display the label text.
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                ),
            )
        }
    }
}

/**
 * Data class representing an item in the bottom navigation bar.
 *
 * @property icon The drawable resource ID for the item's icon.
 * @property label The text label for the item.
 */
data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val label: String
)

/**
 * Preview composable for the NewsBottomNavigation component.
 *
 * This composable allows you to preview the NewsBottomNavigation directly
 * within Android Studio. It demonstrates the bottom navigation bar with
 * three items: Home, Search, and Bookmark, with the Home item selected.
 */
@PreviewLightDark
@Composable
private fun NewsBottomNavigationPreview() {
    NewsAppTheme {
        NewsBottomNavigation(
            items = listOf(
                BottomNavigationItem(icon = R.drawable.ic_home, label = "Home"),
                BottomNavigationItem(icon = R.drawable.ic_search, label = "Search"),
                BottomNavigationItem(icon = R.drawable.ic_bookmark, label = "Bookmark"),
            ),
            selectedItem = 0,
            onItemClick = {},
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}