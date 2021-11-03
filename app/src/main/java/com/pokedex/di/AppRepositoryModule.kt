package com.pokedex.di

import com.pokedex.domain.PokemonRepository
import com.pokedex.domain.PokemonRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class AppRepositoryModule {

    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepositoryImp: PokemonRepositoryImp
    ): PokemonRepository
}