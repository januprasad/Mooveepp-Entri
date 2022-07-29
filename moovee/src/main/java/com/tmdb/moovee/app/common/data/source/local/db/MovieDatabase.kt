package com.tmdb.moovee.app.common.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moovee.engine.utils.typeconverter.TypeConverter
import com.tmdb.moovee.app.screen.popular.data.local.db.dao.PopularMovieDao
import com.tmdb.moovee.app.screen.popular.data.local.db.dao.PopularMovieRemoteDao
import com.tmdb.moovee.app.screen.popular.data.local.entity.PopularMovie
import com.tmdb.moovee.app.screen.popular.data.local.entity.PopularMoviesRemoteKeys

@Database(
    entities = [PopularMovie::class, PopularMoviesRemoteKeys::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun popularMovieDao(): PopularMovieDao
    abstract fun popularMovieRemoteDao(): PopularMovieRemoteDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE
                ?: synchronized(this) {
                    INSTANCE
                        ?: buildDatabase(
                            context
                        ).also {
                            INSTANCE = it
                        }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "MovieDatabase.db"
            )
                .build()
    }
}
