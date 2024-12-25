package com.app.news

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The main application class for the NewsApp.
 *
 * This class is annotated with `@HiltAndroidApp`, which triggers Hilt's code
 * generation and sets up the dependency injection container for the entire
 * application. It serves as the application-level entry point for Hilt.
 */
@HiltAndroidApp
class NewsApp: Application()