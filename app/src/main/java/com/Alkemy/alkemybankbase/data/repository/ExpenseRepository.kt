package com.Alkemy.alkemybankbase.data.repository

import com.Alkemy.alkemybankbase.data.Error
import arrow.core.Either
import com.Alkemy.alkemybankbase.data.datasource.ExpenseRemoteDataSource
import com.Alkemy.alkemybankbase.data.model.ExpenseRequest
import com.Alkemy.alkemybankbase.data.model.ExpenseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExpenseRepository @Inject constructor(private val expenseRemoteDatasource: ExpenseRemoteDataSource) {

    suspend fun saveExpense(request: ExpenseRequest): Flow<Either<Error, ExpenseResponse>> {

        return flow {
            emit(expenseRemoteDatasource.saveExpense(request))
        }

    }

}