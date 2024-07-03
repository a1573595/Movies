package com.a1573595.movies

import com.a1573595.movies.fake.FakeDataSource
import com.a1573595.movies.fake.FakeMovieRepository
import com.a1573595.movies.rule.TestDispatcherRule
import com.a1573595.movies.ui.screen.MovieListUiState
import com.a1573595.movies.ui.screen.MovieUiState
import com.a1573595.movies.ui.screen.MovieViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun movieListUiState_Success() {
        runTest {
            val viewModel = MovieViewModel(movieRepository = FakeMovieRepository())

            assertEquals(
                MovieListUiState.Success(FakeDataSource.discover.movieList),
                viewModel.listUiState
            )
        }
    }

    @Test
    fun movieUiState_Loading() {
        runTest {
            val viewModel = MovieViewModel(movieRepository = FakeMovieRepository())

            assertEquals(
                MovieUiState.Loading,
                viewModel.detailUiState
            )
        }
    }

    @Test
    fun movieUiState_Success() {
        runTest {
            val viewModel = MovieViewModel(movieRepository = FakeMovieRepository())

            viewModel.getMovieDetail(1)

            assertEquals(
                MovieUiState.Success(FakeDataSource.movie),
                viewModel.detailUiState
            )
        }
    }
}