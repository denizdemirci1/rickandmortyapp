package com.denizbutandroid.rickandmortyapp.di

import com.denizbutandroid.rickandmortyapp.data.remote.RickAndMortyService
import com.denizbutandroid.rickandmortyapp.data.repositories.CharacterRepositoryImpl
import com.denizbutandroid.rickandmortyapp.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(
        service: RickAndMortyService,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CharacterRepository {
        return CharacterRepositoryImpl(service, dispatcher)
    }
}
