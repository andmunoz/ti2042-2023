package cl.inacap.apps.cyberpunk2020storemobile.firebase

import cl.inacap.apps.cyberpunk2020storemobile.firebase.model.WeaponModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.Objects

interface MyFirebaseApi {
    @GET("Armas.json")
    fun getWeapons(): Call<Objects>

    @GET("Armas/{id}.json")
    fun getWeapon(@Path("id") id: Int): Call<WeaponModel>

    @POST("Armas.json")
    fun postWeapon(@Body weapon: WeaponModel?): Call<WeaponModel>
}