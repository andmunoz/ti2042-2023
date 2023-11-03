package com.example.pokedesk.api.response

import com.google.gson.annotations.SerializedName

data class PokemonResponse(@SerializedName("name") var name:String, @SerializedName("weight") var weight:Int, @SerializedName("height") var height:Int)
