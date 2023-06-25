package com.example.pokemonapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.pokemonAdapter.RcVeiwAdapter
import com.example.pokemonapp.retrofit.PokemonApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAmCheckOut.setOnClickListener {
            val intent = Intent(this,RecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }
}