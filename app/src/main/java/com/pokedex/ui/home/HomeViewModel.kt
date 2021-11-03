package com.pokedex.ui.home

import androidx.lifecycle.*
import com.pokedex.data.model.*
import com.pokedex.di.IOThread
import com.pokedex.di.MainThread
import com.pokedex.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val compositeDisposable: CompositeDisposable,
    @IOThread private val schedulers: Scheduler,
    @MainThread private val mainThread: Scheduler,

): ViewModel() {

    private var _getPokemonEvent = MutableLiveData<GetPokemonEvent>()
    val getPokemonEvent: LiveData<GetPokemonEvent> = _getPokemonEvent

    fun getPokemons() {
        viewModelScope.launch {
            try {
                val data = repository.getPokemons()
                _getPokemonEvent.value = GetPokemonSuccess(data)
            } catch (e: Exception) {
                _getPokemonEvent.value = GetPokemonFailure(e)
            }
        }
    }

    fun getPokemonWithFlow(): StateFlow<List<Pokemon>> = flow {
        kotlin.runCatching {
            repository.getPokemons()
        }.onSuccess {
            emit(it)
        }.onFailure {
            emit(emptyList<Pokemon>())
        }
    }.stateIn (
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun getPokemonWithFlowAndLiveData() = viewModelScope.launch {
        kotlin.runCatching {
            repository.getPokemons()
        }.onSuccess {
            _getPokemonEvent.value = GetPokemonSuccess(it)
        }.onFailure {
            _getPokemonEvent.value = GetPokemonFailure(Exception(it.localizedMessage))
        }
    }

    fun getPokemonWithLiveData() = liveData {
        try {
            val data = repository.getPokemons()
            emit(data)
        }catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun getPokemonsWithRx2(){
        try {
            compositeDisposable.add(
                repository.getPokemonsWithRx()
                    .subscribeOn(schedulers)
                    .observeOn(mainThread)
                    .subscribe { response ->
                        _getPokemonEvent.value = GetPokemonSuccess(response.results.map { it.toPokemon() })
                    }
            )
        } catch (e: Exception) {
            _getPokemonEvent.value = GetPokemonFailure(e)
        }
    }

    fun getPokemonsWithRx(){
        try {
            compositeDisposable.add(
                repository.getPokemonsWithRx()
                    .subscribeOn(schedulers)
                    .observeOn(mainThread)
                    .subscribe { response ->
                        _getPokemonEvent.value = GetPokemonSuccess(response.results.map { it.toPokemon() })
                    }
            )
        } catch (e: Exception) {
            _getPokemonEvent.value = GetPokemonFailure(e)
        }
    }

}