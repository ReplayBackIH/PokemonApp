package com.example.pokemonapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PokemonEntity::class], version = 1)

abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {

        @Volatile
        private var POKEMON_DATABASE: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            return POKEMON_DATABASE ?: synchronized(this) {
                val pokemonDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java, "pokemon_database"
                ).build()
                POKEMON_DATABASE = pokemonDatabase
                pokemonDatabase
            }
        }

    }
}