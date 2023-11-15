package cl.inacap.apps.pokemondesk.api.response

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count") var count:Int,
    @SerializedName("results") var results:ArrayList<PokemonDetail>
)

data class PokemonDetail(
    @SerializedName("name") var name:String,
    @SerializedName("url") var url:String
)