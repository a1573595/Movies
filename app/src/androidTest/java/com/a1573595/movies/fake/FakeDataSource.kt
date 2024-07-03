package com.a1573595.movies.fake

import com.a1573595.movies.model.Discover
import com.a1573595.movies.model.DiscoverMovie
import com.a1573595.movies.model.Movie

object FakeDataSource {
    val discover = Discover(
        page = 1,
        movieList = listOf(
            DiscoverMovie(
                posterPath = "/posterPath",
                id = 1,
                title = "Title",
            ),
            DiscoverMovie(
                posterPath = "/posterPath",
                id = 2,
                title = "Title",
            ),
            DiscoverMovie(
                posterPath = "/posterPath",
                id = 3,
                title = "Title",
            ),
        )
    )

    val movie = Movie(
        posterPath = "/posterPath",
        id = 1,
        overview = "OverView",
        title = "Title",
    )
}