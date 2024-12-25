package com.app.news.presentation

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
     * Another medium padding value, slightly larger than [MediumPadding1].
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
}