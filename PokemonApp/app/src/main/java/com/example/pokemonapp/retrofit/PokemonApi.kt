package com.example.pokemonapp.retrofit

import retrofit2.http.GET


interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemons() : Pokemons

}