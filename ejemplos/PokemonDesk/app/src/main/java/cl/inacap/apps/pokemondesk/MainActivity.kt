package cl.inacap.apps.pokemondesk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import cl.inacap.apps.pokemondesk.api.PokemonApiServiceAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    // Main widgets objects
    private lateinit var pokemonSelector: Spinner
    private lateinit var pokemonNameText: EditText
    private lateinit var pokemonWeightText: EditText
    private lateinit var pokemonHeightText: EditText

    // Other importante objects
    private val pokemonAdapter = PokemonApiServiceAdapter()
    private val pokemonArray = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set widgets with screen objects
        pokemonSelector = findViewById(R.id.pokemon_selector)
        pokemonNameText = findViewById(R.id.pokemon_name_label)
        pokemonWeightText = findViewById(R.id.pokemon_weight_label)
        pokemonHeightText = findViewById(R.id.pokemon_height_label)

        runBlocking {
            populateSpinner()
        }
    }

    private suspend fun populateSpinner() {
        // Get names of first 50th pokemon
        val pokemonListResponse = GlobalScope.async(Dispatchers.IO) {
            pokemonAdapter.getPokemonList(limit = 20)
        }

        // Create an array with only names of pokemon
        pokemonListResponse.await()?.results?.forEach { pokemon ->
            pokemon["name"]?.let { pokemonName -> pokemonArray.add(pokemonName) }
        }

        // Put in adapter and show in spinner
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pokemonArray)
        with(pokemonSelector) {
            adapter = arrayAdapter
            setSelection(0, false)
            onItemSelectedListener = this@MainActivity
            gravity = Gravity.CENTER
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        runBlocking {
            showDetails(pokemonArray[position])
        }
    }

    private suspend fun showDetails(pokemonName: String) {
        // Get detail of selected pokemon
        val pokemonDetailResponse = GlobalScope.async(Dispatchers.IO) {
            pokemonAdapter.getPokemon(pokemonName)
        }

        // Obtain object result
        val pokemonDetail = pokemonDetailResponse.await()

        // Put in screen
        pokemonNameText.setText(pokemonDetail?.name)
        pokemonWeightText.setText(pokemonDetail?.weight.toString())
        pokemonHeightText.setText(pokemonDetail?.height.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nothing Selected", Toast.LENGTH_LONG).show()
    }

}
