package cl.inacap.apps.pokemondesk.api

import cl.inacap.apps.pokemondesk.api.response.PokemonListResponse
import cl.inacap.apps.pokemondesk.api.response.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    fun getPokemonList(@Query("offset") offset: Int = 0,
                       @Query("limit") limit: Int = 20): Call<PokemonListResponse>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name:String): Call<PokemonResponse>
}