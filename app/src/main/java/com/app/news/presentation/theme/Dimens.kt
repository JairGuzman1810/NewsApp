package com.app.news.presentation.theme

import androidx.compose.ui.unit.dp

/**
 * Object containing dimension values used throughout the application's UI.
 *
 * This object centralizes the definition of common spacing and sizing values,
 * ensuring consistency across different composables and screens.
 */
object Dimens {
    /**
     * A medium padding value, typically used for spacing between elements.
     *
     * This padding is used in various places where a moderate amount of space
     * is needed, such as between an image and text.
     */
    val MediumPadding1 = 24.dp

    /**
     * Another medium padding value, slightly larger than MediumPadding1.
     *
     * This padding is used in places where a bit more space is needed, such as
     * the horizontal padding around text content.
     */
    val MediumPadding2 = 30.dp

    /**
     * The size of the indicators, such as those used in a horizontal pager.
     *
     * This value defines the diameter of the indicator dots.
     */
    val IndicatorSize = 14.dp

    /**
     * The width of the page indicator row.
     *
     * This value defines the total width of the row that contains the page
     * indicator dots. It's used to ensure that the indicator has enough space
     * to display all the dots.
     */
    val PageIndicatorWidth = 52.dp

    /**
     * The size of the article card in the home screen.
     *
     * This value defines the height and width of the article card.
     */
    val ArticleCardSize = 96.dp

    /**
     * An extra small padding value, used for very fine spacing adjustments.
     *
     * This padding is used in places where a minimal amount of space is
     * needed, such as between small icons and text.
     */
    val ExtraSmallPadding = 3.dp

    /**
     * Another extra small padding value, slightly larger than ExtraSmallPadding.
     *
     * This padding is used in places where a bit more space is needed than
     * ExtraSmallPadding, such as between text and a small icon.
     */
    val ExtraSmallPadding2 = 6.dp

    /**
     * The size of small icons used throughout the app.
     *
     * This value defines the height and width of small icons, such as the
     * time icon in the article card.
     */
    val SmallIconSize = 11.dp

    /**
     * The size of the icons used in the search bar.
     *
     * This value defines the height and width of the icons, such as the
     * search icon in the search bar.
     */
    val IconSize = 20.dp

    /**
     * The height of the image displayed in the details screen for an article.
     *
     * This value defines the height of the image that is shown at the top of
     * the details screen for a news article.
     */
    val ArticleImageHeight = 248.dp
}