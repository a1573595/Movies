package com.a1573595.movies

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.a1573595.movies.fake.FakeDataSource
import com.a1573595.movies.fake.FakeMovieRepository
import com.a1573595.movies.ui.screen.MovieDetailScreen
import com.a1573595.movies.ui.screen.MovieViewModel
import org.junit.Rule
import org.junit.Test

class MovieDetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun movieList_verifyContent() {
        val viewModel = MovieViewModel(FakeMovieRepository())
        composeTestRule.setContent {
            MovieDetailScreen(viewModel = viewModel) {}
        }

        viewModel.getMovieDetail(1)

        composeTestRule.onNodeWithContentDescription(FakeDataSource.movie.id.toString()).assertExists()
    }
}