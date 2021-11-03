package com.pokedex.data.remote

import com.pokedex.data.model.GetPokemons
import com.pokedex.data.model.Pokemon
import com.pokedex.data.model.PokemonDetail
import com.pokedex.data.service.ApiService
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPokemons(): GetPokemons = apiService.getPokemons()
    suspend fun getPokemonDetail(id: String): PokemonDetail = apiService.getPokemonDetail(id.toInt())
    fun getPokemonsWithRx(): Observable<GetPokemons> = apiService.getPokemonsWithRx()
}
