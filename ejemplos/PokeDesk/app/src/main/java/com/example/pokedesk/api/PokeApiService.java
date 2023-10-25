package com.example.pokedesk.api;

import com.example.pokedesk.api.response.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApiService {

    @GET("pokemon")
    Call<PokemonResponse> getPokemon();

}
