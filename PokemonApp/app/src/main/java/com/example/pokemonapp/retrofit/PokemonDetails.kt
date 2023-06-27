package com.example.pokemonapp.retrofit

data class PokemonDetails(
    val height: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int
)

data class Sprites(
    val front_default: String,
)

data class Type(
    val slot: Int,
    val type: TypeDetails
)

data class TypeDetails(
    val name: String,
)