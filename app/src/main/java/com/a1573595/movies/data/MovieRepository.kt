package com.a1573595.movies.data

import com.a1573595.movies.api.MovieApi
import com.a1573595.movies.model.DiscoverMovie
import com.a1573595.movies.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MovieRepository {
    suspend fun getDiscoverMovieList(page: Int): List<DiscoverMovie>

    suspend fun getMovieDetail(id: Int): Movie
}

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : MovieRepository {
    override suspend fun getDiscoverMovieList(page: Int): List<DiscoverMovie> = withContext(ioDispatcher) {
        movieApi.getDiscoverMovieList(page).movieList
    }

    override suspend fun getMovieDetail(id: Int): Movie = withContext(ioDispatcher) {
        movieApi.getMovieDetail(id)
    }
}