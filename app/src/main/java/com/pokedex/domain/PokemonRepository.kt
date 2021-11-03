package com.pokedex.domain

import com.pokedex.data.model.GetPokemons
import com.pokedex.data.model.Pokemon
import com.pokedex.data.model.PokemonDetail
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemons(): List<Pokemon>
    suspend fun getPokemonDetail(id: String): PokemonDetail
    fun getPokemonsWithRx(): Observable<GetPokemons>
}