package com.example.myfirebaseexample.api.response

import com.google.gson.annotations.SerializedName

data class WeaponResponse(
    @SerializedName("01_Nombre") var name: String,
    @SerializedName("14_Dano") var damage: String,
    @SerializedName("16_Costo") var cost: Long
)
