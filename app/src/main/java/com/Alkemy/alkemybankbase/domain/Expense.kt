package com.Alkemy.alkemybankbase.domain

data class Expense (
    val amount: Int, //"amount": 500,
    val concept: String, //"concept": "Pago de honorarios",
    val date: String, //"date": "2022-10-26 15:00:00",
    val type: String, //"type": "topup|payment",
    val accountId: Int, //"accountId": 1,
    val userId: Int, //"userId": 4,
    val to_account_id: Int? //"to_account_id": 5
)