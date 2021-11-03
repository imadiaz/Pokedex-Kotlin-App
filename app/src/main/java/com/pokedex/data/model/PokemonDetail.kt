package com.pokedex.data.model

import com.google.gson.annotations.SerializedName


abstract class PokemonDetailEvent()
data class PokemonDetailSuccess(val data: PokemonDetail): PokemonDetailEvent()
data class PokemonDetailFailure(val exception: Exception): PokemonDetailEvent()


data class PokemonDetail (
    @SerializedName("base_experience")
    val baseExperience: Long,
    val forms: List<Species>,
    val height: Long,
    val id: Long,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Long,
    val species: Species,
    val sprites: Sprites,
    val weight: Long
)

//data class Ability (
//    val ability: Species,
//
//    @SerializedName("is_hidden")
//    val isHidden: Boolean,
//
//    val slot: Long
//)


data class Species (
    val name: String,
    val url: String
)

data class Move (
    val move: Species
)


data class Sprites (
    @SerializedName("back_default")
    val backDefault: String,

    @SerializedName("back_shiny")
    val backShiny: String,

    @SerializedName("front_default")
    val frontDefault: String,

    @SerializedName("front_shiny")
    val frontShiny: String,
)
