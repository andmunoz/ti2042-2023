package cl.inacap.apps.pokemondesk.api.response

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(@SerializedName("count") var count:Int,
                               @SerializedName("results") var results:List<LinkedHashMap<String, String>>)
