package com.pokedex.data.service

import com.pokedex.data.model.GetPokemons
import com.pokedex.data.model.PokemonDetail
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("pokemon")
    suspend fun getPokemons(): GetPokemons

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetail

    @GET("pokemon")
    fun getPokemonsWithRx(): Observable<GetPokemons>
}