package com.example.pokemonapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao()
interface PokemonDao {

    @Query("SELECT * FROM pokemons")
    fun getAllPokemonInfo() : LiveData<List<PokemonEntity>>

    @Insert
    fun insertPokemonInfo(pokemon : PokemonEntity)
}