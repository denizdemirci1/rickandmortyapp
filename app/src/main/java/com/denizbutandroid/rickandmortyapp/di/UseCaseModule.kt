package com.denizbutandroid.rickandmortyapp.di

import com.denizbutandroid.rickandmortyapp.domain.character.GetCharacterInfoUseCase
import com.denizbutandroid.rickandmortyapp.domain.character.GetCharactersUseCase
import com.denizbutandroid.rickandmortyapp.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(
        characterRepository: CharacterRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepository)
    }

    @Provides
    @Singleton
    fun provideGetCharacterInfoUseCase(
        characterRepository: CharacterRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetCharacterInfoUseCase {
        return GetCharacterInfoUseCase(characterRepository, dispatcher)
    }
}
