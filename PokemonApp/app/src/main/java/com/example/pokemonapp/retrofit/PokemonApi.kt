package com.example.pokemonapp.retrofit

import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemons() : Pokemons

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetails(@Path("id") id : Int) : PokemonDetails

}