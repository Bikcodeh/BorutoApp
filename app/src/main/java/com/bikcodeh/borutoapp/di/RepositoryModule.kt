package com.bikcodeh.borutoapp.di

import android.content.Context
import com.bikcodeh.borutoapp.data.repository.DataStoreOperationsImpl
import com.bikcodeh.borutoapp.data.repository.Repository
import com.bikcodeh.borutoapp.domain.repository.DataStoreOperations
import com.bikcodeh.borutoapp.domain.use_cases.UseCases
import com.bikcodeh.borutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.bikcodeh.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository)
        )
    }
}