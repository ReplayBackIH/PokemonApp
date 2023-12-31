package com.example.pokemonapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityRecyclerViewBinding
import com.example.pokemonapp.fragments.DetailedPokemonFragment
import com.example.pokemonapp.pokemonAdapter.RcVeiwAdapter
import com.example.pokemonapp.retrofit.OnPokemonSelectedListener
import com.example.pokemonapp.retrofit.PokemonApi
import com.example.pokemonapp.retrofit.PokemonDetails
import com.example.pokemonapp.retrofit.SinglePokemon
import com.example.pokemonapp.viewmodel.PokemonViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecyclerViewActivity : AppCompatActivity(),OnPokemonSelectedListener {

    lateinit var binding: ActivityRecyclerViewBinding
    lateinit var adapter: RcVeiwAdapter
    private lateinit var viewModel : PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = RcVeiwAdapter(this)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = adapter

        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]

        viewModel.pokemonsPublic.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.getPokemons()
    }

    override fun onPokemonSelected(pokemonId: Int) {
        val detailedPokemonFragment = DetailedPokemonFragment().apply {
            arguments = Bundle().apply {
                putInt("pokemonId",pokemonId)
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerForFragments,detailedPokemonFragment)
            .addToBackStack(null)
            .commit()
    }
}