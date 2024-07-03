package com.a1573595.movies.data

import com.a1573595.movies.api.MovieApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface AppContainer {
    val movieRepository: MovieRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.themoviedb.org/3/"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .build()

    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }

    override val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl(movieApi)
    }
}