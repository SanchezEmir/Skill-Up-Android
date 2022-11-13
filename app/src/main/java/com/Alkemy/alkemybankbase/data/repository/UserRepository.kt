package com.Alkemy.alkemybankbase.data.repository

import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.data.datasource.UserRemoteDatasource
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDatasource: UserRemoteDatasource) {

    suspend fun loginUser(email: String, password: String) = userRemoteDatasource.auth(email, password)

    suspend fun createUser(request: UserRegisterRequest) = userRemoteDatasource.registerUser(request)

    suspend fun getTransactions() = userRemoteDatasource.getTransactions()

}