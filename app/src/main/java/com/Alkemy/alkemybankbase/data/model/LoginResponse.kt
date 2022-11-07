package com.Alkemy.alkemybankbase.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("error")
    val error: String?,
    @SerializedName("status")
    val status: Int?,
)

