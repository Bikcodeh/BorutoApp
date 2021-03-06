package com.bikcodeh.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.bikcodeh.borutoapp.data.local.BorutoDatabase
import com.bikcodeh.borutoapp.data.repository.LocalDataSourceImpl
import com.bikcodeh.borutoapp.domain.repository.LocalDataSource
import com.bikcodeh.borutoapp.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): BorutoDatabase = Room.databaseBuilder(
        context,
        BorutoDatabase::class.java,
        BORUTO_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(borutoDatabase: BorutoDatabase): LocalDataSource {
        return LocalDataSourceImpl(borutoDatabase = borutoDatabase)
    }
}