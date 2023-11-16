package com.example.pokedesk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedesk.api.response.PokemonListResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private fun getPokemonList(): PokemonListResponse {
        val call = PokeApiAdapter().getRetrofit().create(PokeApiService::class.java).getPokemonList().execute()
        val pokemonList = call.body() as PokemonListResponse
        return pokemonList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val pokemonList = getPokemonList()
            println("${pokemonList.count} pokemones encontrados")
        }
    }

}