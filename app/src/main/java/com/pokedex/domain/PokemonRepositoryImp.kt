package com.pokedex.domain


import com.pokedex.data.model.GetPokemons
import com.pokedex.data.model.Pokemon
import com.pokedex.data.model.PokemonDetail
import com.pokedex.data.model.toPokemon
import com.pokedex.data.remote.PokemonDataSource
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImp @Inject constructor(
    private val dataSource: PokemonDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : PokemonRepository {


    override suspend fun getPokemons(): List<Pokemon> = withContext(coroutineDispatcher) {
        dataSource.getPokemons().results.map { it.toPokemon() }
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetail = withContext(coroutineDispatcher) {
        dataSource.getPokemonDetail(id)
    }

    override fun getPokemonsWithRx(): Observable<GetPokemons> {
        return dataSource.getPokemonsWithRx()
    }
}


