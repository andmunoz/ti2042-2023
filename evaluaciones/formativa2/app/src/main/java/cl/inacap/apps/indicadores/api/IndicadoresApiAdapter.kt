package cl.inacap.apps.indicadores.api

import cl.inacap.apps.indicadores.api.response.IndicadorResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IndicadoresApiAdapter {
    var URL_BASE = "https://miindicador/api/"
    var indicadoresApi = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getIndicador(tipo:String, fecha:String): IndicadorResponse? {
        var call = indicadoresApi.create(IndicadoresApi::class.java)
            .getIndicador(tipo, fecha).execute()
        println(call.toString())
        return call.body()
    }
}