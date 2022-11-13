package com.Alkemy.alkemybankbase.data.model

//Request User Register
data class UserRegisterRequest(
    val first_name: String, //"first_name": "Juan"
    val last_name: String, //"last_name": "Perez"
    val email: String, //"email": "juanperez@example.com"
    val password: String, //"password": "abc123"
    val roleId: Int? = 2, //"roleId": 2
    val points: Int? = 50 //"points": 50
)

data class UserLoginRequest(
    val email: String,
    val password: String
)

data class ExpenseRequest(
    val amount: Int, //"amount": 500,
    val concept: String, //"concept": "Pago de honorarios",
    val date: String, //"date": "2022-10-26 15:00:00",
    val type: String? = "payment", //"type": "topup|payment",
    val accountId: Int? = 1, //"accountId": 1,
    val userId: Int? = 2074, //"userId": 4,
    val to_account_id: Int? = null //"to_account_id": 5
)