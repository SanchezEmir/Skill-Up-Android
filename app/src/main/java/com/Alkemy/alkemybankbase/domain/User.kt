package com.Alkemy.alkemybankbase.domain

data class User (
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
    val roleId: Int? = 2,
    val points: Int? = 50
)