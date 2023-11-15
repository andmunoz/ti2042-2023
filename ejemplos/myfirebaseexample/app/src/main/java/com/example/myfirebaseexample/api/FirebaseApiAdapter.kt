package com.example.myfirebaseexample.api

import com.example.myfirebaseexample.api.response.WeaponResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirebaseApiAdapter {
    private var URL_BASE = "https://cyberpunk-database.firebaseio.com/"
    private val firebaseApi = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWeapon(id: String): WeaponResponse? {
        val call = firebaseApi.create(FirebaseApi::class.java).getWeapon(id).execute()
        return call.body()
    }

    fun setWeapon(weapon: WeaponResponse): WeaponResponse? {
        val call = firebaseApi.create(FirebaseApi::class.java).setWeapon(weapon).execute()
        return weapon
    }
}