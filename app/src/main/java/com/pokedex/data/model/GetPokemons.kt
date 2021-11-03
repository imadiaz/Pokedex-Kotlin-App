package com.pokedex.data.model


data class GetPokemons (
    val count: Long,
    val next: String,
    val results: List<Result>
)


data class Result (
    val name: String,
    val url: String
)

data class Pokemon(
    val id: String,
    val name: String,
    val image: String
)


abstract class GetPokemonEvent()
data class GetPokemonSuccess(val list: List<Pokemon>): GetPokemonEvent()
data class GetPokemonFailure(val exception: Exception): GetPokemonEvent()

fun Result.getPokemonId(): String {
    return if (url != null) {
        val urlData = url.split("/")
        return if (urlData.size > 6) {
            urlData[6]
        } else {
            ""
        }
    } else {
        ""
    }
}

fun Result.toPokemon(): Pokemon {
    val id = this.getPokemonId()
    return Pokemon(
        id,
        name,
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    )
}

fun PokemonDetail.getPokemonImage(): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
}