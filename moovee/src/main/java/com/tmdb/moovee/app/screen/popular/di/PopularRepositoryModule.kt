package com.tmdb.moovee.app.screen.popular.di

import com.tmdb.moovee.app.screen.popular.data.apihelper.IPopularMoviesRepository
import com.tmdb.moovee.app.screen.popular.data.apihelper.PopularMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class PopularRepositoryModule {

    @Provides
    fun providePopularMoviesRepository(iPopularMoviesRepository: IPopularMoviesRepository): PopularMoviesRepository {
        return iPopularMoviesRepository
    }
}
