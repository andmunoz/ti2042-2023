package cl.inacap.apps.pokemondesk.api

import cl.inacap.apps.pokemondesk.api.response.PokemonListResponse
import cl.inacap.apps.pokemondesk.api.response.PokemonResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonApiServiceAdapter {
    private var URL_BASE = "https://pokeapi.co/api/v2/"
    private val pokemonApi = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPokemonList(offset: Int = 0, limit: Int = 20): PokemonListResponse? {
        val call = pokemonApi.create(PokemonApiService::class.java).getPokemonList(offset, limit).execute()
        return call.body()
    }

    fun getPokemon(name:String): PokemonResponse? {
        val call = pokemonApi.create(PokemonApiService::class.java).getPokemon(name).execute()
        return call.body()
    }
}