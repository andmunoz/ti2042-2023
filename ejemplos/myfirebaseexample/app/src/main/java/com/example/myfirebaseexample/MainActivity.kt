package com.example.myfirebaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        buttonLoad = findViewById(R.id.buttonLoad)
        buttonLoad.setOnClickListener {
            Toast.makeText(this, "Cargando información", Toast.LENGTH_SHORT).show()
            val weaponId = idField.text.toString()
            runBlocking {
                getWeaponFromApi(weaponId)
            }
        }

        buttonSave = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            Toast.makeText(this, "Guardando información", Toast.LENGTH_SHORT).show()
            val weaponName = nameField.text.toString()
            val damage = damageField.text.toString()
            val cost = costField.text.toString().toLong()
            runBlocking {
                val weapon = WeaponResponse("", weaponName, damage, cost)
                sendWeaponToApi(weapon)
            }
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
        idField.setText(weapon?.id)
        nameField.setText(weapon?.name)
        damageField.setText(weapon?.damage)
        costField.setText("${weapon?.cost}")
    }
}