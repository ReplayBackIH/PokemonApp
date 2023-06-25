package com.example.pokemonapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityRecyclerViewBinding
import com.example.pokemonapp.fragments.DetailedPokemonFragment
import com.example.pokemonapp.pokemonAdapter.RcVeiwAdapter
import com.example.pokemonapp.retrofit.PokemonApi
import com.example.pokemonapp.retrofit.SinglePokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecyclerViewActivity : AppCompatActivity(), RcVeiwAdapter.OnItemClickListener {

    lateinit var binding: ActivityRecyclerViewBinding
    lateinit var adapter: RcVeiwAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = RcVeiwAdapter(this)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val pokemonApi = retrofit.create(PokemonApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val listOfPokemons = pokemonApi.getAllPokemons()

            runOnUiThread{
                adapter.submitList(listOfPokemons.results)
            }
        }
    }

    override fun onItemClick(pokemon: SinglePokemon) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val detailedFragment = DetailedPokemonFragment()

        fragmentTransaction.replace(R.id.containerForFragments,detailedFragment)
        fragmentTransaction.commit()
    }
}