package com.Alkemy.alkemybankbase.data.datasource

import android.content.SharedPreferences
import arrow.core.Either
import com.Alkemy.alkemybankbase.data.Error
import com.Alkemy.alkemybankbase.data.model.ExpenseRequest
import com.Alkemy.alkemybankbase.data.model.ExpenseResponse
import com.Alkemy.alkemybankbase.data.server.RemoteService
import com.Alkemy.alkemybankbase.data.tryCall
import com.Alkemy.alkemybankbase.utils.Constants
import javax.inject.Inject

class ExpenseServerDataSource @Inject
constructor(private val remoteService: RemoteService,
            private val sharedPreferences: SharedPreferences) : ExpenseRemoteDataSource {


    override suspend fun saveExpense(request: ExpenseRequest): Either<Error, ExpenseResponse> = tryCall {

        val token = sharedPreferences.getString(Constants.TOKEN, "") ?: ""
        remoteService.createExpense("Bearer $token", request)

    }


}