package cl.inacap.apps.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.inacap.apps.mymovies.api.MoviesApiAdapter
import cl.inacap.apps.mymovies.api.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private var moviesApi = MoviesApiAdapter()
    private var moviesList = linkedMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            getMoviesFromApi()
        }
    }

    private suspend fun getMoviesFromApi() {
        val response = GlobalScope.async(Dispatchers.IO) { moviesApi.getMovies() }
        val movies = response.await()
        movies?.forEach { key, movie ->
            moviesList.put(key, movie.titulo)
        }
        println(moviesList)
    }

    private suspend fun getMovieFromApi(key: String) {
        val response = GlobalScope.async(Dispatchers.IO) { moviesApi.getMovie(key) }
        val movie = response.await()
        println(movie)
    }

    private suspend fun sendMovieToApi(movie: Movie) {
        val response = GlobalScope.async(Dispatchers.IO) { moviesApi.setMovie(movie)}
        val apiResponse = response.await()
        println(apiResponse)
    }
}