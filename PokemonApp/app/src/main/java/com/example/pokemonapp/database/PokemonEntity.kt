package com.example.pokemonapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pokemons")
data class PokemonEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "weight")
    val weight : Int,

    @ColumnInfo(name = "height")
    val height : Int,

    @ColumnInfo(name = "type")
    val type : String,

    @ColumnInfo(name = "image")
    val image : String





)
