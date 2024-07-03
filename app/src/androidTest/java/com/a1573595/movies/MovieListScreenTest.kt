package com.a1573595.movies

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.ComposeNavigator
import com.a1573595.movies.fake.FakeDataSource
import com.a1573595.movies.fake.FakeMovieRepository
import com.a1573595.movies.ui.screen.MovieListScreen
import com.a1573595.movies.ui.screen.MovieViewModel
//import androidx.navigation.testing.TestNavHostController
import org.junit.Rule
import org.junit.Test

/// todo mock viewmodel、retrofit?
/// todo mock retrofit
class MovieListScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun movieList_verifyContent() {
        composeTestRule.setContent {
            MovieListScreen(viewModel = MovieViewModel(FakeMovieRepository())) {}
        }

        FakeDataSource.discover.movieList.forEach {
            composeTestRule.onNodeWithContentDescription(it.id.toString()).assertExists()
        }
    }
}