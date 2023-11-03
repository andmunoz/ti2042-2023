package com.example.pokedesk

import com.example.pokedesk.api.response.PokemonListResponse
import com.example.pokedesk.api.response.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokeApiService {
    @GET("pokemon")
    fun getPokemonList(): Call<PokemonListResponse>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name:String): Call<PokemonResponse>
}