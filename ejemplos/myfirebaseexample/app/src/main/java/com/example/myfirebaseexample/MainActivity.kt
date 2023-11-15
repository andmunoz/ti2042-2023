package com.example.myfirebaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myfirebaseexample.api.FirebaseApiAdapter
import com.example.myfirebaseexample.api.response.WeaponResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    // Referenciar campos de las interfaz
    private lateinit var idField: EditText
    private lateinit var nameField: EditText
    private lateinit var damageField: EditText
    private lateinit var costField: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button

    // Referenciar la API
    private var firebaseApi = FirebaseApiAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idField = findViewById(R.id.idField)
        nameField = findViewById(R.id.nameField)
        damageField = findViewById(R.id.damangeField)
        costField = findViewById(R.id.costField)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)

        runBlocking {
            // getWeaponFromApi("312")
            val weapon = WeaponResponse(name = "Pistola de Agua", damage = "0", cost = 1)
            sendWeaponToApi(weapon)
        }
    }

    private suspend fun getWeaponFromApi(id: String) {
        val weaponResponse = GlobalScope.async(Dispatchers.IO) {
            firebaseApi.getWeapon(id)
        }
        val weapon = weaponResponse.await()
        idField.setText(id)
        nameField.setText(weapon?.name)
        damageField.setText(weapon?.damage)
        costField.setText("${weapon?.cost}")
    }

    private suspend fun sendWeaponToApi(weapon: WeaponResponse) {
        val weaponResponse = GlobalScope.async(Dispatchers.IO) {
            firebaseApi.setWeapon(weapon)
        }
        val weapon = weaponResponse.await()
        idField.setText("New")
        nameField.setText(weapon?.name)
        damageField.setText(weapon?.damage)
        costField.setText("${weapon?.cost}")
    }
}