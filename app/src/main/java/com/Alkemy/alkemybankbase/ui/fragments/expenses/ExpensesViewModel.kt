package com.Alkemy.alkemybankbase.ui.fragments.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Alkemy.alkemybankbase.data.model.ExpenseRequest
import com.Alkemy.alkemybankbase.usescases.RegisterExpense
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(private val registerExpense: RegisterExpense): ViewModel() {

    private val _state = MutableStateFlow<ExpensesState>(ExpensesState.Init)
    val state: StateFlow<ExpensesState> get() = _state.asStateFlow()

    fun saveExpense(request: ExpenseRequest) {

        viewModelScope.launch {

            _state.value = ExpensesState.IsLoading(true)

            try {

                val response = withContext(Dispatchers.IO) {
                    registerExpense(request)
                }

                response.collect(){

                    it.fold(

                        { error ->
                            _state.value = ExpensesState.Error(error.toString())
                        },
                        { response ->
                            _state.value = ExpensesState.Success(response.concept)
                        }
                    )

                }


            } catch (e: Exception) {
                _state.value = ExpensesState.Error(e.message.toString())
            } finally {
                _state.value = ExpensesState.IsLoading(false)
            }

        }

    }

    sealed class ExpensesState {
        object Init: ExpensesState()
        data class IsLoading(val isLoading: Boolean): ExpensesState()
        data class Success(val response: String): ExpensesState()
        data class Error(val message: String): ExpensesState()
    }

}