package cl.inacap.apps.mymovies.api

import retrofit2.Call
import cl.inacap.apps.mymovies.api.models.Movie
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MoviesApi {
    @GET("peliculas.json")
    fun getMovies(): Call<MutableMap<String, Movie>>

    @GET("peliculas/{key}.json")
    fun getMovie(
        @Path("key") key: String
    ): Call<Movie>

    @POST("peliculas.json")
    fun setMovie(
        @Body movie: Movie
    ): Call<Movie>
}