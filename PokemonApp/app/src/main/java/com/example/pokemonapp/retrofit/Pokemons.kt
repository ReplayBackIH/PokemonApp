package com.example.pokemonapp.retrofit

data class Pokemons (
    val count : Int,
    val next : String,
    val previous : String?,
    val results : List<SinglePokemon>
        )
