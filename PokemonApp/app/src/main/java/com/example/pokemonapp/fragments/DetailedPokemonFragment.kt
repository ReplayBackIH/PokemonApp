package com.example.pokemonapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.pokemonapp.R
import com.example.pokemonapp.activities.RecyclerViewActivity

class DetailedPokemonFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonGoBack : Button = view.findViewById(R.id.btnGoBack)
        buttonGoBack.setOnClickListener {
            val intent = Intent(activity,RecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }
}
