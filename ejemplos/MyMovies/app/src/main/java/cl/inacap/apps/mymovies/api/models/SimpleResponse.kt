package cl.inacap.apps.mymovies.api.models

import com.google.gson.annotations.SerializedName

data class SimpleResponse (
    @SerializedName("name") var name:String
)