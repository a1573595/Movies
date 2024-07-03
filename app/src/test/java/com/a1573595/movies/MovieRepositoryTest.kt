package com.a1573595.movies

import com.a1573595.movies.data.MovieRepositoryImpl
import com.a1573595.movies.fake.FakeDataSource
import com.a1573595.movies.fake.FakeMovieApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieRepositoryTest {
    @Test
    fun getDiscoverMovieList() {
        runTest {
            val repository = MovieRepositoryImpl(movieApi = FakeMovieApi())
            assertEquals(FakeDataSource.discover.movieList, repository.getDiscoverMovieList(1))
        }
    }

    @Test
    fun getMovieDetail() {
        runTest {
            val repository = MovieRepositoryImpl(movieApi = FakeMovieApi())
            assertEquals(FakeDataSource.movie, repository.getMovieDetail(1))
        }
    }
}