package cl.inacap.apps.cyberpunk2020storemobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.inacap.apps.cyberpunk2020storemobile.firebase.MyFirebaseApiAdapter
import cl.inacap.apps.cyberpunk2020storemobile.firebase.model.WeaponModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private var myFirebaseApi = MyFirebaseApiAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            var weapons = retrieveWeapons()
            println("${weapons?.size} weapons are stored!")

            var weapon = retrieveWeapon(124)
            println("Weapon found: ${weapon}")

            var newWeapon = WeaponModel(1000, "Prueba", "Prueba", 0, "Prueba", "Prueba", "Prueba", 100)
            var result = saveWeapon(newWeapon)
            println("New weapon saved!")
        }
    }

    private suspend fun retrieveWeapons(): ArrayList<WeaponModel>? {
        println("Calling Service")
        val callingResponse = GlobalScope.async(Dispatchers.IO) {
            myFirebaseApi.getWeapons()
        }
        println("Waiting Response")
        val weapons = callingResponse.await()
        println("Done!")
        return weapons
    }

    private suspend fun retrieveWeapon(id: Int): WeaponModel? {
        println("Calling Service")
        val callingResponse = GlobalScope.async(Dispatchers.IO) {
            myFirebaseApi.getWeapon(id)
        }
        println("Waiting Response")
        val weapon = callingResponse.await()
        println("Done!")
        return weapon
    }

    private suspend fun saveWeapon(weapon: WeaponModel) {
        println("Calling Service")
        val callingResponse = GlobalScope.async(Dispatchers.IO) {
            myFirebaseApi.postWeapon(weapon)
        }
        println("Waiting Response")
        val id = callingResponse.await()
        println("Done!")
    }
}