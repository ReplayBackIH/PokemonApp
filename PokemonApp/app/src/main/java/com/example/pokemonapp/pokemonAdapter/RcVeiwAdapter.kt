package com.example.pokemonapp.pokemonAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.databinding.RecyclerViewItemBinding
import com.example.pokemonapp.retrofit.OnPokemonSelectedListener
import com.example.pokemonapp.retrofit.Pokemons
import com.example.pokemonapp.retrofit.SinglePokemon

class RcVeiwAdapter(val listener: OnPokemonSelectedListener) :
    ListAdapter<SinglePokemon, RcVeiwAdapter.PokemonHolder>(ElementComparator()) {


   inner class PokemonHolder(view: View) : ViewHolder(view) {

        private var binding = RecyclerViewItemBinding.bind(view)

        init {
            itemView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                   val pokemonId = position + 1
                    listener.onPokemonSelected(pokemonId)
                }
            }
        }

        fun insertData(pokemon: SinglePokemon, positionOfPokemonInList: Int) = with(binding) {
            tvNameOfPokemon.text = pokemon.name
            tvPokemonPosition.text = (positionOfPokemonInList + 1).toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return PokemonHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.insertData(getItem(position), position)
    }

    class ElementComparator : DiffUtil.ItemCallback<SinglePokemon>() {
        override fun areItemsTheSame(oldItem: SinglePokemon, newItem: SinglePokemon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SinglePokemon, newItem: SinglePokemon): Boolean {
            return oldItem == newItem
        }
    }
}

