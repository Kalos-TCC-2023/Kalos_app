package br.senai.sp.jandira.kalos_app.model

import com.google.gson.annotations.SerializedName

data class BaseResponseExercicio<T>(
    @SerializedName("informacoes")
    var data: T? = null

)
