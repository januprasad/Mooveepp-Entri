package com.tmdb.moovee.app.common.di

import com.tmdb.moovee.app.screen.popular.data.apihelper.PopularMovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providePopularMoviesService(retrofit: Retrofit): PopularMovieService =
        retrofit.create(PopularMovieService::class.java)
}
