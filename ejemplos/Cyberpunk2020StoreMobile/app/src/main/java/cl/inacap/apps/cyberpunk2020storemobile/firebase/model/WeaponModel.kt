package cl.inacap.apps.cyberpunk2020storemobile.firebase.model

import com.google.gson.annotations.SerializedName

data class WeaponModel(
    @SerializedName("00_Id") var id:Int,
    @SerializedName("01_Nombre") var name:String,
    @SerializedName("02_Tipo") var type:String,
    @SerializedName("11_Precision") var presicion:Int,
    @SerializedName("12_Fiabilidad") var reliability:String,
    @SerializedName("13_Alcance") var range:String,
    @SerializedName("14_Dano") var damage:String,
    @SerializedName("16_Costo") var cost:Long
)