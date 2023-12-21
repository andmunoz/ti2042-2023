package cl.inacap.apps.mymovies.api.models

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("imdb") var imdb: String,
    @SerializedName("titulo") var titulo: String,
    @SerializedName("director") var director: String,
    @SerializedName("agno") var agno: Int,
    @SerializedName("genero") var genero: String,
    @SerializedName("duracion") var duracion: Int,
    @SerializedName("puntuacion") var puntuacion: Int
)