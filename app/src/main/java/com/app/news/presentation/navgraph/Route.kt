package com.app.news.presentation.navgraph

/**
 * Sealed class representing the different routes in the app's navigation graph.
 *
 * This class defines the possible destinations within the app, providing a
 * type-safe way to refer to routes. Each route is represented as a data
 * object, making it easy to use in navigation logic.
 *
 * @param route The string representation of the route.
 */
sealed class Route(
    val route: String
) {
    /**
     * Route for the onboarding screen.
     */
    data object OnBoardingScreen : Route("onBoardingScreen")

    /**
     * Route for the home screen.
     */
    data object HomeScreen : Route("homeScreen")

    /**
     * Route for the search screen.
     */
    data object SearchScreen : Route("searchScreen")

    /**
     * Route for the bookmark screen.
     */
    data object BookmarkScreen : Route("bookmarkScreen")

    /**
     * Route for the details screen.
     */
    data object DetailsScreen : Route("detailsScreen")

    /**
     * Route for the app start navigation graph.
     *
     * This route is used to group screens related to the initial app setup,
     * such as the onboarding screen.
     */
    data object AppStartNavigation : Route("appStartNavigation")

    /**
     * Route for the news navigation graph.
     *
     * This route is used to group screens related to the main news section
     * of the app.
     */
    data object NewsNavigation : Route("newsNavigation")

    /**
     * Route for the news navigator screen.
     *
     * This is the main screen within the news navigation graph, where the
     * user can browse news articles.
     */
    data object NewsNavigatorScreen : Route("newsNavigator")
}