package com.example.pokemonapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.activities.RecyclerViewActivity
import com.example.pokemonapp.databinding.FragmentDetailedPokemonBinding
import com.example.pokemonapp.retrofit.PokemonApi
import com.example.pokemonapp.retrofit.PokemonDetails
import com.example.pokemonapp.viewmodel.PokemonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DetailedPokemonFragment : Fragment() {

    private lateinit var viewModel : PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonId = arguments?.getInt("pokemonId")

        viewModel = ViewModelProvider(requireActivity())[PokemonViewModel::class.java]

        viewModel.detailedPokemonPublic.observe(requireActivity(), Observer {
            insertDetailedData(view,it)
        })

        if (pokemonId != null){
            viewModel.getDetailedPokemons(pokemonId)
        }


        val buttonGoBack: Button = view.findViewById(R.id.btnGoBack)
        buttonGoBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun insertDetailedData(view : View, detailedPokemonData : PokemonDetails?){
        val nameOfPokemon : TextView = view.findViewById(R.id.tvFragmentNameOfPokemon)
        val weightOfPokemon : TextView = view.findViewById(R.id.weightDescriptionTextView)
        val heightOfPokemon : TextView = view.findViewById(R.id.heightDescriptionTextView)
        val typeOfPokemon : TextView = view.findViewById(R.id.typeDescriptionTextView)
        val pokemonImage : ImageView = view.findViewById(R.id.pokemonImage)

        if (detailedPokemonData != null) {
            nameOfPokemon.text = detailedPokemonData.name
        }
        if (detailedPokemonData != null) {
            val weightOfPokemonInKg = detailedPokemonData.weight / 10
            weightOfPokemon.text = weightOfPokemonInKg.toString()
        }
        if (detailedPokemonData != null) {
            val heightOfPokemonInCm = detailedPokemonData.height * 10
            heightOfPokemon.text = heightOfPokemonInCm.toString()
        }
        if (detailedPokemonData != null) {
            val pokemonTypeNameList = mutableListOf<String>()

            detailedPokemonData.types.forEach { type ->
                pokemonTypeNameList.add(type.type.name)
            }
            val pokemonTypeNameString = pokemonTypeNameList.joinToString(", ")
            typeOfPokemon.text=pokemonTypeNameString
        }
        if (detailedPokemonData != null){
                Glide.with(view.context).load(detailedPokemonData.sprites.front_default).into(pokemonImage)
        }

    }

}