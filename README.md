# News App - Android Application

This repository contains the source code for the News App, an Android application that provides users with the latest news articles from various sources. Users can browse, search, save, and share news articles, all within a modern and intuitive interface.

## Key Features

*   **Onboarding:** A guided introduction to the app's features for first-time users.
*   **Home Screen:** Displays a curated list of news articles from various sources.
*   **Search Functionality:** Allows users to search for specific news articles using keywords.
*   **Bookmark Articles:** Users can save their favorite articles for later reading.
*   **Article Details:** Provides a detailed view of each article, including a larger image, title, description, and source.
*   **Share Articles:** Users can share articles with others via various platforms.
*   **Open in Browser:** Allows users to open the full article in their default web browser.
*   **Dark/Light Theme:** Supports both dark and light themes, automatically adjusting based on the device's settings.

## Technologies and Patterns

This project utilizes a modern Android development stack, incorporating industry-standard patterns and best practices:

*   **Kotlin:** The primary programming language, ensuring concise and expressive code.
*   **Jetpack Compose:** A declarative UI toolkit for building native Android UIs with a reactive approach.
*   **MVVM (Model-View-ViewModel):** Used in combination with MVI to manage UI state and logic.
*   **MVI (Model-View-Intent):** A unidirectional data flow architecture, promoting a clear separation of concerns and predictable state management.
*   **Clean Architecture:** A robust architecture that emphasizes separation of concerns, testability, and maintainability.
*   **Hilt:** A dependency injection framework for Android, simplifying dependency management.
*   **Room:** A persistence library for local data storage, used for saving bookmarked articles.
*   **Retrofit:** A type-safe HTTP client for Android and Java, used for making network requests to the News API.
*   **Async Image (Coil Compose):** An image loading library for Jetpack Compose, providing efficient image handling.
*   **DataStore Preferences:** Used to store simple key-value pairs, such as whether the onboarding screen has been viewed.
*   **KSP (Kotlin Symbol Processing):** A tool for building lightweight compiler plugins.
*   **androidx-core-splashscreen:** For implementing the splash screen.
*   **Kotlin Parcelize:** For easily passing objects between screens.
*   **Material Theme:** Implements Material Design components for a modern and consistent user interface.
*   **androidx.navigation.compose:** For managing navigation between screens, including nested navigation graphs.
*   **State Management:** The app uses state management to persist data between screen rotations.
*   **Coroutines:** Kotlin Coroutines are used for asynchronous programming, ensuring a smooth and responsive user experience.
*   **Dark and Light Theme**: The app support dark and light theme based on the device settings.

## Dependencies

### Libraries
```
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
androidx-ui = { group = "androidx.compose.ui", name = "ui" }
androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
androidx-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
androidx-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
androidx-material3 = { group = "androidx.compose.material3", name = "material3" }
androidx-core-splashscreen = { group = "androidx.core", name = "core-splashscreen", version.ref = "coreSplashscreen" }
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hiltAndroid" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-compiler", version.ref = "hiltCompiler" }
androidx-hilt-navigation-compose = { group = "androidx.hilt", name = "hilt-navigation-compose", version.ref = "hiltNavigationCompose" }
retrofit = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
converter-gson = { group = "com.squareup.retrofit2", name = "converter-gson", version.ref = "converterGson" }
coil-compose = { group = "io.coil-kt", name = "coil-compose", version.ref = "coilCompose" }
androidx-datastore-preferences = { group = "androidx.datastore", name = "datastore-preferences", version.ref = "datastorePreferences" }
androidx-foundation = { group = "androidx.compose.foundation", name = "foundation", version.ref = "foundation" }
androidx-paging-runtime = { group = "androidx.paging", name = "paging-runtime", version.ref = "pagingRuntime" }
androidx-paging-compose = { group = "androidx.paging", name = "paging-compose", version.ref = "pagingCompose" }
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "roomRuntime" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "roomCompiler" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "roomKtx" }
```

### Plugins
```
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
hilt-dagger = {id = "com.google.dagger.hilt.android", version.ref = "hiltVersion"}
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
kotlin-parcelize = { id = "org.jetbrains.kotlin.plugin.parcelize", version.ref = "kotlin" }
```

## Installation and Setup
This section provides instructions on how to clone, build, and run the Focus Timer Android application on your local machine using Android Studio.

1.  **Clone the Repository:**

    -   Open your terminal or command prompt.
    -   Navigate to the directory where you want to store the project.
    -   Use the following command to clone the repository:
```
 git clone https://github.com/JairGuzman1810/NewsApp/
```
2.  **Open in Android Studio:**

    -   Launch Android Studio.
    -   If you see the "Welcome to Android Studio" screen, select "Open an Existing Project."
    -   If Android Studio is already open, go to "File" > "Open..."
    -   Navigate to the directory where you cloned the repository and select it.
    -   Click "Open."

3.  **Sync Project with Gradle Files:**

    -   Once the project is open, Android Studio will prompt you to sync the project with the Gradle files.
    -   Click "Sync Now" in the notification bar that appears at the top of the editor.
    -   Alternatively, you can go to "File" > "Sync Project with Gradle Files."

4.  **Build the Project:**

    -   After the Gradle sync is complete, go to "Build" > "Make Project" to build the project.
    -   This step compiles the code and checks for any errors.

5.  **Run the App:**

    -   Connect an Android device to your computer via USB, or start an Android emulator.
    -   In Android Studio, select your connected device or emulator from the device dropdown menu in the toolbar.
    -   Click the "Run" button (green play icon) in the toolbar to run the app on your selected device or emulator.

6.  **API Key:**

    *   The project requires an API key from [newsapi.org](https://newsapi.org/).
    *   **If it doesn't exist,** create a file named `local.properties` in the root directory of the project.
    *   Open the `local.properties` file.
    *   Add the following line to the `local.properties` file, replacing `YOUR_API_KEY` with your actual API key from newsapi.org. **The variable name must be `API_KEY`**: API_KEY="YOUR_API_KEY" 
  
## Screenshots

### Light Theme

<div style="display:flex; flex-wrap:wrap; justify-content:space-between;">
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Light-1.jpg" alt="App-Light-1" width="180"/>
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Light-2.jpg" alt="App-Light-2" width="180"/>
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Light-3.jpg" alt="App-Light-3" width="180"/>
</div>

<div style="display:flex; flex-wrap:wrap; justify-content:space-between;">
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Light-4.jpg" alt="App-Light-4" width="180"/>
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Light-5.jpg" alt="App-Light-5" width="180"/>
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Light-6.jpg" alt="App-Light-6" width="180"/>
</div>

### Dark Theme

<div style="display:flex; flex-wrap:wrap; justify-content:space-between;">
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Dark-1.jpg" alt="App-Dark-1" width="180"/>
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Dark-2.jpg" alt="App-Dark-2" width="180"/>
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Dark-3.jpg" alt="App-Dark-3" width="180"/>
</div>

<div style="display:flex; flex-wrap:wrap; justify-content:space-between;">
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Dark-4.jpg" alt="App-Dark-4" width="180"/>
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Dark-5.jpg" alt="App-Dark-5" width="180"/>
    <img src="https://github.com/JairGuzman1810/NewsApp/blob/master/resources/App-Dark-6.jpg" alt="App-Dark-6" width="180"/>
</div>

