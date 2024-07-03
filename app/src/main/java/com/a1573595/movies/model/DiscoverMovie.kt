package com.a1573595.movies.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class Discover(
    val page: Int,
    @SerialName("results")
    val movieList: List<DiscoverMovie>,
)

@Serializable()
data class DiscoverMovie(
//    val adult: Boolean,
    /// 橫向
//    @SerialName("backdrop_path")
//    val backdropPath: String,
    /// 直向
    @SerialName("poster_path")
    val posterPath: String,
    val id: Int,
    val title: String,
)