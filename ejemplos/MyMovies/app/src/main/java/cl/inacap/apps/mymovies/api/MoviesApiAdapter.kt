package cl.inacap.apps.mymovies.api

import cl.inacap.apps.mymovies.api.models.Movie
import cl.inacap.apps.mymovies.api.models.SimpleResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiAdapter {
    private var URL_BASE = "https://ejemplo-firebase-657d0-default-rtdb.firebaseio.com/"
    private var firebaseApi = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMovies(): MutableMap<String, Movie>? {
        return firebaseApi.create(MoviesApi::class.java).getMovies().execute().body()
    }

    fun getMovie(key: String): Movie? {
        return firebaseApi.create(MoviesApi::class.java).getMovie(key).execute().body()
    }

    fun setMovie(movie: Movie): Movie? {
        return firebaseApi.create(MoviesApi::class.java).setMovie(movie).execute().body()
    }
}