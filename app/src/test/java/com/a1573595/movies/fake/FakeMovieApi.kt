package com.a1573595.movies.fake

import com.a1573595.movies.api.MovieApi
import com.a1573595.movies.model.Discover
import com.a1573595.movies.model.Movie

class FakeMovieApi : MovieApi {
    override suspend fun getDiscoverMovieList(page: Int): Discover = FakeDataSource.discover

    override suspend fun getMovieDetail(movieId: Int): Movie = FakeDataSource.movie
}