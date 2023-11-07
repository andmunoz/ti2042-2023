package cl.inacap.apps.indicadores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import cl.inacap.apps.indicadores.api.IndicadoresApiAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.lang.Float.parseFloat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var dolarField: EditText
    lateinit var euroField: EditText
    lateinit var ufField: EditText
    lateinit var utmField: EditText

    private var indicadoresApi = IndicadoresApiAdapter()
    private var decimalFormat: DecimalFormat = DecimalFormat("#,###.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dolarField = findViewById(R.id.dolar_field)
        euroField = findViewById(R.id.euro_field)
        ufField = findViewById(R.id.uf_field)
        utmField = findViewById(R.id.utm_field)

        runBlocking {
            showIndicador("dolar", dolarField)
            showIndicador("euro", euroField)
            showIndicador("uf", ufField)
            showIndicador("utm", utmField)
        }
    }

    private suspend fun showIndicador(tipo: String, field: EditText) {
        println("Calling service")
        val indicadorResponse = GlobalScope.async {
            indicadoresApi.getIndicador(tipo, "03-11-2023")
        }
        println("Waiting response")
        val indicador = indicadorResponse.await()
        val nombre = indicador?.nombre
        val unidadMedida = indicador?.unidadMedida
        val valor = parseFloat(indicador?.serie?.get(0)?.valor.toString())
        println("1 ${nombre} = ${valor} ${unidadMedida}")
        field.setText("${decimalFormat.format(valor)} ${unidadMedida}")
    }
}