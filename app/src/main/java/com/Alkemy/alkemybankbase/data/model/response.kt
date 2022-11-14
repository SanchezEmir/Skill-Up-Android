package com.Alkemy.alkemybankbase.data.model

data class UserRegisterResponse (
    val first_name: String, //"first_name": "Juan"
    val last_name:String, //"last_name": "Perez"
    val email: String, //"email": "juanperez@example.com"
    val password: String, //"password": "abc123"
    val roleId: Int, //"roleId": 2
    val points: Int //"points": 50
)

data class UserLoginResponse(
    val accessToken:String
)

data class TransactionsResponse(
    val previousPage: String?,
    val nextPage: String?,
    val data: MutableList<TransactionModel>
)

data class ExpenseResponse(
    val id: Int, //"id": 3002,
    val amount: String, //"amount": 500,
    val concept: String, //"concept": "Pago de honorarios",
    val date: String, //"date": "2022-10-26T00:00:00.000Z",
    val type: String, //"type": "payment",
    val accountId: Int, //"accountId": 1,
    val userId:Int, //"userId": 4,
    val updatedAt: String, //"updatedAt": "2022-11-13T09:59:37.734Z",
    val createdAt: String //"createdAt": "2022-11-13T09:59:37.734Z"
)

data class ErrorResponse(
    val error: String, //"error": "No autorizado"
    val status:Int //"status": 401
){
    override fun toString(): String {
        return "ErrorResponse(error= $error, status= $status)"
    }
}

