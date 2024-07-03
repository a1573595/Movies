package com.a1573595.movies.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
//    val adult: Boolean,
//    /// 橫向
//    @SerialName("backdrop_path")
//    val backdropPath: String,
    /// 直向
    @SerialName("poster_path")
    val posterPath: String,
//    val budget: Long,
//    val homepage: String,
    val id: Int,
    val overview: String,
//    @SerialName("release_date")
//    val releaseDate: String,
//    val status: String,
    val title: String,
//    @SerialName("vote_average")
//    val voteAverage: Float,
//    @SerialName("vote_count")
//    val voteCount: Int,
)