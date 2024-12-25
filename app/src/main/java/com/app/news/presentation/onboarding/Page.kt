package com.app.news.presentation.onboarding

import androidx.annotation.DrawableRes
import com.app.news.R

/**
 * Represents a single page in the onboarding flow.
 *
 * Each page contains a title, a description, and an image to guide the user
 * through the application's initial setup or introduction.
 *
 * @property title The title of the page, displayed prominently to the user.
 * @property description A detailed description of the page's content or purpose.
 * @property image The drawable resource ID of the image associated with this page.
 */
data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

/**
 * A list of Page objects that define the content of the onboarding screens.
 *
 * This list contains the data for each page that will be displayed in the
 * onboarding horizontal pager. Each Page includes a title, description, and
 * an image resource.
 */
val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding3
    )
)
