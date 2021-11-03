package com.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pokedex.data.model.PokemonDetail
import com.pokedex.data.model.PokemonDetailFailure
import com.pokedex.data.model.PokemonDetailSuccess
import com.pokedex.data.model.getPokemonImage
import com.pokedex.databinding.FragmentDetailBinding
import com.pokedex.ui.adapter.PropertyItemAdapter
import com.pokedex.ui.adapter.toPropertyItem
import com.pokedex.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!
    private var movesAdapter: PropertyItemAdapter? = null
    private var formsAdapter: PropertyItemAdapter? = null
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.setPokemonId(it.getString("ID","0"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initObservers()
        getPokemonDetail()
    }

    private fun initComponents() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        movesAdapter = PropertyItemAdapter(requireContext())
        binding.moveRecyclerAdapter.adapter = movesAdapter
        binding.moveRecyclerAdapter.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        formsAdapter = PropertyItemAdapter(requireContext())
        binding.formsRecyclerAdapter.adapter = formsAdapter
        binding.formsRecyclerAdapter.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initObservers() {
        viewModel.pokemonDetailEvent.observe(viewLifecycleOwner, { result ->
            when(result) {
                is PokemonDetailSuccess -> {
                    setPokemonInformation(result.data)
                }

                is PokemonDetailFailure -> {
                    Toast.makeText(requireContext(), "${result.exception.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setPokemonInformation(pokemonDetail: PokemonDetail) = with(binding) {
        txtPokemonName.text = pokemonDetail.name.replaceFirstChar { it.uppercase() }
        txtPokemonWeight.text = "Weight ${pokemonDetail.weight}"
        txtPokemonHeight.text = "Height ${pokemonDetail.height}"
        txtPokemonExperience.text = "Experience ${pokemonDetail.baseExperience}"
        movesAdapter?.submitList(pokemonDetail.moves.map { it.toPropertyItem() })
        formsAdapter?.submitList(pokemonDetail.forms.map { it.toPropertyItem() })

        Glide.with(requireContext())
            .load(pokemonDetail.getPokemonImage())
            .circleCrop()
            .into(pokemonImg)

        Glide.with(requireContext())
            .load(pokemonDetail.sprites.backDefault)
            .into(pokemonImgBack)

        Glide.with(requireContext())
            .load(pokemonDetail.sprites.frontDefault)
            .into(pokemonImgFront)

        layoutPokemonInformation.visibility = View.VISIBLE
        circularProgress.visibility = View.GONE
    }

    private fun getPokemonDetail() = viewModel.getPokemonDetail()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}