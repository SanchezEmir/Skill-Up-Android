package com.Alkemy.alkemybankbase.data.model

data class ListaMovimientos(
    var amount: Int,
    var concept: String,
    var date: String,
    var type: String,
    var accountId: Int,
    var userId: Int,
    var to_account_id: Int,
)
