package com.Alkemy.alkemybankbase.data.model

import java.util.*

class TransactionModel (
    val id: Int,
    val amount: String,
    val concept: String,
    val date: Date,
    val type: String,
    val accountId: Int,
    val userId: Int,
    val to_account_id: Int,
)