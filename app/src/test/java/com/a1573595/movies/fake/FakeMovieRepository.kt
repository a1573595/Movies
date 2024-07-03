package com.a1573595.movies.fake

import com.a1573595.movies.data.MovieRepository
import com.a1573595.movies.model.DiscoverMovie
import com.a1573595.movies.model.Movie

class FakeMovieRepository : MovieRepository {
    override suspend fun getDiscoverMovieList(page: Int): List<DiscoverMovie> = FakeDataSource.discover.movieList

    override suspend fun getMovieDetail(id: Int): Movie = FakeDataSource.movie
}