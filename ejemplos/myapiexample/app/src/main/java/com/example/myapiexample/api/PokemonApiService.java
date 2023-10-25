package com.example.myapiexample.api;

import com.example.myapiexample.api.response.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonApiService {

    @GET("pokemon")
    Call<PokemonResponse> getPokemon();

}
