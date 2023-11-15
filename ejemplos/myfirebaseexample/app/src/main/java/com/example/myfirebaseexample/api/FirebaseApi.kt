package com.example.myfirebaseexample.api

import com.example.myfirebaseexample.api.response.WeaponResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FirebaseApi {
    @GET("Armas/{id}.json")
    fun getWeapon(
        @Path("id") id: String
    ): Call<WeaponResponse>

    @POST("Armas.json")
    fun setWeapon(
        @Body() body: WeaponResponse
    ): Call<WeaponResponse>
}