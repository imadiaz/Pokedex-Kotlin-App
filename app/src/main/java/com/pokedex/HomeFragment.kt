package com.pokedex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pokedex.data.model.GetPokemonFailure
import com.pokedex.data.model.GetPokemonSuccess
import com.pokedex.data.model.Pokemon
import com.pokedex.data.remote.PokemonDataSource
import com.pokedex.data.service.ApiService
import com.pokedex.databinding.FragmentHomeBinding
import com.pokedex.domain.PokemonRepositoryImp
import com.pokedex.ui.adapter.PokemonAdapter
import com.pokedex.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), PokemonAdapter.OnPokemonClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var pokemonAdapter: PokemonAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initObservers()
        getPokemons()
    }


    private fun initComponents() {
        pokemonAdapter = PokemonAdapter(requireContext(), this)
        binding.pokemonRecyclerView.adapter = pokemonAdapter
        val gridLayoutManager =  GridLayoutManager(requireContext(), 2)
        binding.pokemonRecyclerView.layoutManager = gridLayoutManager
    }

    private fun initObservers() {
        viewModel.getPokemonEvent.observe(viewLifecycleOwner, { result ->
            when (result) {
                is GetPokemonSuccess -> {
                    pokemonAdapter?.submitList(result.list)
                }
                is GetPokemonFailure -> {
                    Toast.makeText(requireContext(), "Ups! ${result.exception.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        })

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.getPokemonWithFlow().collect {
//                pokemonAdapter?.submitList(it)
//            }
//        }


//        viewModel.getPokemonWithLiveData().observe(viewLifecycleOwner, { result ->
//            pokemonAdapter?.submitList(result)
//        })
    }

    private fun getPokemons() {
       // viewModel.getPokemonWithFlowAndLiveData()
        //viewModel.getPokemons()
        viewModel.getPokemonsWithRx()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onPokemonClickListener(pokemon: Pokemon) {
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundleOf("ID" to pokemon.id))
    }

}