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

data class ErrorResponse(
    val error: String, //"error": "No autorizado"
    val status:Int //"status": 401
){
    override fun toString(): String {
        return "ErrorResponse(error= $error, status= $status)"
    }
}