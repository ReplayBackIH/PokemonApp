package com.example.pokemonapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.pokemonapp.R
import com.example.pokemonapp.activities.RecyclerViewActivity
import com.example.pokemonapp.databinding.FragmentDetailedPokemonBinding
import com.example.pokemonapp.retrofit.PokemonApi
import com.example.pokemonapp.retrofit.PokemonDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DetailedPokemonFragment : Fragment() {

    private val retrofit: Retrofit? = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val pokemonApi = retrofit?.create(PokemonApi::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonId = arguments?.getInt("pokemonId")

        CoroutineScope(Dispatchers.IO).launch {
            var detailedPokemonData : PokemonDetails? = null
            if (pokemonId != null) {
                 detailedPokemonData = pokemonApi?.getPokemonDetails(pokemonId)
            }
            withContext(Dispatchers.Main){
                insertDetailedData(view,detailedPokemonData)
            }
        }


        val buttonGoBack: Button = view.findViewById(R.id.btnGoBack)
        buttonGoBack.setOnClickListener {
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        }
    }

    private fun insertDetailedData(view : View, detailedPokemonData : PokemonDetails?){
        val nameOfPokemon : TextView = view.findViewById(R.id.tvFragmentNameOfPokemon)
        val weightOfPokemon : TextView = view.findViewById(R.id.weightDescriptionTextView)
        val heightOfPokemon : TextView = view.findViewById(R.id.heightDescriptionTextView)
        val typeOfPokemon : TextView = view.findViewById(R.id.typeDescriptionTextView)

        if (detailedPokemonData != null) {
            nameOfPokemon.text = detailedPokemonData.name
        }
        if (detailedPokemonData != null) {
            weightOfPokemon.text = detailedPokemonData.weight.toString()
        }
        if (detailedPokemonData != null) {
            heightOfPokemon.text = detailedPokemonData.height.toString()
        }
        if (detailedPokemonData != null) {
            typeOfPokemon.text = detailedPokemonData.types.toString()
        }


    }

}
