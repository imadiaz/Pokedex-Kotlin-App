package com.pokedex.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokedex.data.model.PokemonDetailEvent
import com.pokedex.data.model.PokemonDetailFailure
import com.pokedex.data.model.PokemonDetailSuccess
import com.pokedex.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val EMPTY_STRING = ""

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    private val _pokemonDetailEvent = MutableLiveData<PokemonDetailEvent>()
    val pokemonDetailEvent: LiveData<PokemonDetailEvent> = _pokemonDetailEvent

    private val _pokemonId = MutableLiveData(EMPTY_STRING)

    fun setPokemonId(id: String) {
        _pokemonId.value = id
    }

    fun getPokemonDetail() {
        viewModelScope.launch {
            try {
                val data = pokemonRepository.getPokemonDetail(_pokemonId.value ?: EMPTY_STRING)
                _pokemonDetailEvent.value = PokemonDetailSuccess(data)
            }catch (e: Exception) {
                _pokemonDetailEvent.value = PokemonDetailFailure(e)
            }
        }
    }
}