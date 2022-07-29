package com.tmdb.moovee.app.screen.popular.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.moovee.app.screen.popular.data.apihelper.PopularMoviesRepository
import com.tmdb.moovee.app.screen.popular.data.local.entity.PopularMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class PopularMovieViewModel @ViewModelInject constructor(
    private val repository: PopularMoviesRepository
) : ViewModel() {

    suspend fun getPopularMovies(): Flow<PagingData<PopularMovie>> {
        return repository
            .getPopularMovies()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
    }
}
