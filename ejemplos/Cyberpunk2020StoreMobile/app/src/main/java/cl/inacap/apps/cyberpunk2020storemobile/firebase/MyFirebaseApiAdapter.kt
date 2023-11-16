package cl.inacap.apps.cyberpunk2020storemobile.firebase

import cl.inacap.apps.cyberpunk2020storemobile.firebase.model.WeaponModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyFirebaseApiAdapter {
    var URL_BASE = "https://cyberpunk-database.firebaseio.com/"
    var firebaseApi = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWeapons(): ArrayList<WeaponModel>? {
        var call =
            firebaseApi.create(MyFirebaseApi::class.java).getWeapons().execute()
        val data = arrayListOf<WeaponModel>();
        println(call.body().toString())
        return data
    }

    fun getWeapon(id: Int): WeaponModel? {
        var call =
            firebaseApi.create(MyFirebaseApi::class.java).getWeapon(id).execute()
        return call.body()
    }

    fun postWeapon(weapon: WeaponModel): Boolean {
        var call =
            firebaseApi.create(MyFirebaseApi::class.java).postWeapon(weapon).execute()
        return call.body() != null
    }
}