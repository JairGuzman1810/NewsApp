package com.app.news.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.news.R
import com.app.news.domain.model.Article
import com.app.news.presentation.bookmark.BookmarkScreen
import com.app.news.presentation.bookmark.BookmarkViewModel
import com.app.news.presentation.details.DetailsEvent
import com.app.news.presentation.details.DetailsScreen
import com.app.news.presentation.details.DetailsViewModel
import com.app.news.presentation.home.HomeScreen
import com.app.news.presentation.home.HomeViewModel
import com.app.news.presentation.navgraph.Route
import com.app.news.presentation.news_navigator.components.BottomNavigationItem
import com.app.news.presentation.news_navigator.components.NewsBottomNavigation
import com.app.news.presentation.search.SearchScreen
import com.app.news.presentation.search.SearchViewModel
import com.app.news.presentation.theme.NewsAppTheme

/**
 * Composable function that manages the navigation between different screens
 * in the news application.
 *
 * This function uses a NavHost to define the navigation graph and
 * Scaffold to provide a basic layout structure with a bottom navigation bar.
 *
 * @param modifier Modifier for styling and layout customization of the
 *                 navigation host.
 */
@Composable
fun NewsNavigator(
    modifier: Modifier = Modifier
) {

    // Define the items for the bottom navigation bar.
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, label = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, label = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, label = "Bookmark"),
        )
    }

    // Create a NavController to manage navigation within the NavHost.
    val navController = rememberNavController()

    // Get the current back stack entry to track navigation state.
    val backstackState = navController.currentBackStackEntryAsState().value

    // Remember the currently selected item in the bottom navigation bar.
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    // Update the selected item based on the current route.
    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    // Determine if the bottom navigation bar should be visible.
    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.SearchScreen.route ||
                backstackState?.destination?.route == Route.BookmarkScreen.route
    }

    // Scaffold provides the basic screen layout.
    Scaffold(
        modifier = modifier
            .fillMaxSize(), // Make the scaffold fill the entire screen.
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation( // Display the bottom navigation bar.
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) { padding -> // Padding values for the content inside the Scaffold.

        val bottomPadding = padding.calculateBottomPadding()

        // NavHost defines the navigation graph.
        NavHost(
            navController = navController, // Use the created NavController.
            startDestination = Route.HomeScreen.route, // Set the initial screen.
            modifier = Modifier
                .padding(bottom = bottomPadding) // Apply padding to the content.
        ) {

            // Define the composable for the Home screen.
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()

                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            // Define the composable for the Search screen.
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(
                            navController = navController,
                            article = it
                        )
                    }
                )
            }

            // Define the composable for the Details screen.
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(
                        LocalContext.current,
                        viewModel.sideEffect,
                        Toast.LENGTH_LONG
                    ).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
            }

            // Define the composable for the Bookmark screen.
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetails = {
                        navigateToDetails(
                            navController = navController,
                            article = it
                        )
                    }
                )
            }
        }
    }
}

/**
 * Navigates to a specific tab in the bottom navigation bar.
 *
 * @param navController The NavController to use for navigation.
 * @param route The route of the destination tab.
 */
fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

/**
 * Navigates to the details screen for a specific article.
 *
 * @param navController The NavController to use for navigation.
 * @param article The article to display in the details screen.
 */
private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}

/**
 * Preview composable for the NewsNavigator component.
 *
 * This composable allows you to preview the NewsNavigator directly within
 * Android Studio. It demonstrates the navigation flow between the Home,
 * Search, and Bookmark screens.
 */
@PreviewLightDark
@Composable
fun NewsNavigatorPreview() {
    NewsAppTheme {
        NewsNavigator(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}