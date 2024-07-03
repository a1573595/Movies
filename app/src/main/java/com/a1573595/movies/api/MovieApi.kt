package com.a1573595.movies.api

import com.a1573595.movies.model.Discover
import com.a1573595.movies.model.Movie
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val apiKey =
    "YourAPIKey"

interface MovieApi {
    @Headers("Authorization: Bearer $apiKey")
    @GET("discover/movie")
    suspend fun getDiscoverMovieList(@Query("page") page: Int): Discover

    @Headers("Authorization: Bearer $apiKey")
    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") movieId: Int): Movie
}