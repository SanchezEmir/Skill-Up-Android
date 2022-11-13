package com.Alkemy.alkemybankbase.data.server

import com.Alkemy.alkemybankbase.data.model.*
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteService {

    @POST("/auth/login")
    suspend fun auth(@Body request: UserLoginRequest): UserLoginResponse

    @POST("/users")
    suspend fun createUser(@Body request: UserRegisterRequest): UserRegisterResponse

    @GET("/transactions")
    suspend fun getTransactions(): TransactionResponse
}