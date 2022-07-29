package com.tmdb.moovee.app.screen.popular.data.apihelper

import androidx.paging.PagingData
import com.tmdb.moovee.app.screen.popular.data.local.entity.PopularMovie
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {

    suspend fun getPopularMovies(): Flow<PagingData<PopularMovie>>
}
