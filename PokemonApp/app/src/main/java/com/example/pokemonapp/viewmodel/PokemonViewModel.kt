package com.example.pokemonapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.database.PokemonDao
import com.example.pokemonapp.database.PokemonDatabase
import com.example.pokemonapp.database.PokemonEntity
import com.example.pokemonapp.retrofit.PokemonApi
import com.example.pokemonapp.retrofit.PokemonDetails
import com.example.pokemonapp.retrofit.Pokemons
import com.example.pokemonapp.retrofit.SinglePokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PokemonViewModel(application: Application) : AndroidViewModel(application) {

    private val pokemonDao : PokemonDao
    val allPokemons : LiveData<List<PokemonEntity>>
    private val pokemonApi: PokemonApi
    private val retrofit : Retrofit

    private val pokemons = MutableLiveData<List<SinglePokemon>>()
    val pokemonsPublic : LiveData<List<SinglePokemon>> = pokemons

    private val detailedPokemon = MutableLiveData<PokemonDetails>()
    val detailedPokemonPublic : LiveData<PokemonDetails> = detailedPokemon


    init {
        val database = PokemonDatabase.getDatabase(application)
        pokemonDao = database.pokemonDao()
        allPokemons = pokemonDao.getAllPokemonInfo()

        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        pokemonApi = retrofit.create(PokemonApi::class.java)
    }

    fun getPokemons(){
        viewModelScope.launch(Dispatchers.IO){
            val listOfPokemons  = pokemonApi.getAllPokemons()
             pokemons.postValue(listOfPokemons.results)
        }
    }

    fun getDetailedPokemons(pokemonId : Int){
        viewModelScope.launch(Dispatchers.IO){
            val listOfDetailedPokemons = pokemonApi.getPokemonDetails(pokemonId)
            detailedPokemon.postValue(listOfDetailedPokemons)
        }
    }


}