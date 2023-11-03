package com.example.pokedesk

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeApiAdapter() {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}