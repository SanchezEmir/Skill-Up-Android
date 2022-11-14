package com.Alkemy.alkemybankbase.data.datasource

import com.Alkemy.alkemybankbase.data.Error
import arrow.core.Either
import com.Alkemy.alkemybankbase.data.model.ExpenseRequest
import com.Alkemy.alkemybankbase.data.model.ExpenseResponse

interface ExpenseRemoteDataSource {

    suspend fun saveExpense(request: ExpenseRequest) : Either<Error, ExpenseResponse>

}