package com.Alkemy.alkemybankbase.usescases

import com.Alkemy.alkemybankbase.data.model.ExpenseRequest
import com.Alkemy.alkemybankbase.data.repository.ExpenseRepository
import javax.inject.Inject

class RegisterExpense @Inject constructor(private val expenseRepository: ExpenseRepository) {

    suspend operator fun invoke(request: ExpenseRequest) = expenseRepository.saveExpense(request)

}