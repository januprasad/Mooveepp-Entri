package com.tmdb.moovee.app.common.extension

import com.tmdb.moovee.app.common.data.entity.Movies
import com.tmdb.moovee.app.common.data.entity.MoviesResponse
import com.tmdb.moovee.app.screen.popular.data.local.entity.PopularMovie

fun MoviesResponse.mapDataToPopularMovies(): Movies =
    with(this) {
        Movies(
            total = total,
            page = page,
            movies = results.map {
                PopularMovie(
                    it.id,
                    it.posterPath,
                    it.title,
                    it.overview
                )
            }
        )
    }
