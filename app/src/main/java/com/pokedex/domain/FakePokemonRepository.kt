package com.pokedex.domain

import com.pokedex.data.model.Pokemon

object FakePokemonRepository {
    fun getFakeData(): List<Pokemon> {
        return listOf<Pokemon>(
            Pokemon(id="1", name="bulbasaur", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
            Pokemon(id="2", name="ivysaur", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"),
            Pokemon(id="3", name="venusaur", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"),
            Pokemon(id="4", name="charmander", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"),
            Pokemon(id="5", name="charmeleon", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png"),
            Pokemon(id="6", name="charizard", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png"),
            Pokemon(id="7", name="squirtle", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"),
            Pokemon(id="8", name="wartortle", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/8.png"),
            Pokemon(id="9", name="blastoise", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png"),
            Pokemon(id="10", name="caterpie", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10.png"),
            Pokemon(id="11", name="metapod", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/11.png"),
            Pokemon(id="12", name="butterfree", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/12.png"),
            Pokemon(id="13", name="weedle", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/13.png"),
            Pokemon(id="14", name="kakuna", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/14.png"),
            Pokemon(id="15", name="beedrill", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/15.png"),
            Pokemon(id="16", name="pidgey", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/16.png"),
            Pokemon(id="17", name="pidgeotto", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/17.png"),
            Pokemon(id="18", name="pidgeot", image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/18.png"),
            Pokemon(id="19", name="rattata", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/19.png"),
            Pokemon(id="20", name="raticate", image="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/20.png")
        )
    }
}