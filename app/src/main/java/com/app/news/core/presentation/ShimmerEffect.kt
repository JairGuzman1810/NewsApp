package com.app.news.core.presentation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource
import com.app.news.R

/**
 * Modifier function to apply a shimmer effect to a composable.
 *
 * This modifier creates an infinite transition that animates the alpha
 * value of a background color, creating a shimmer effect. The label
 * parameter is set to "shimmerAnimation" to allow for better inspection
 * in the Animation Preview.
 *
 * @return A Modifier with the shimmer effect applied.
 */
fun Modifier.shimmerEffect() = composed {
    // Create an infinite transition that will be used to animate the alpha value.
    val transition = rememberInfiniteTransition(label = "shimmerAnimation")

    // Animate the alpha value between 0.2f and 0.9f.
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "shimmerAlpha" // Label for the alpha animation.
    ).value

    // Apply the background color with the animated alpha value.
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}