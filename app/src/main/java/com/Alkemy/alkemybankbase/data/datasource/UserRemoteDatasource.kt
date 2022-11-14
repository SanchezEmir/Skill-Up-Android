package com.Alkemy.alkemybankbase.data.datasource

import arrow.core.Either
import com.Alkemy.alkemybankbase.data.Error
import com.Alkemy.alkemybankbase.data.model.TransactionModel
import com.Alkemy.alkemybankbase.data.model.TransactionsResponse
import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.domain.User
import com.Alkemy.alkemybankbase.domain.UserLogin

interface UserRemoteDatasource {

    suspend fun auth(email:String, password:String) : Either<Error, UserLogin>

    suspend fun registerUser(request: UserRegisterRequest) : Either<Error, User>

    suspend fun getTransactions() : Either<Error, TransactionsResponse>

}