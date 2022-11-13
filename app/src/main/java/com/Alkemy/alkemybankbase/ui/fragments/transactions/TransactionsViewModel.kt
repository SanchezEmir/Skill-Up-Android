package com.Alkemy.alkemybankbase.ui.fragments.transactions

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Alkemy.alkemybankbase.data.model.TransactionModel
import com.Alkemy.alkemybankbase.usescases.RequestTransactions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val requestTransactions: RequestTransactions
) : ViewModel() {

    private val _state = MutableLiveData<TransactionsState>(TransactionsState.Init)
    val state: MutableLiveData<TransactionsState> get() = _state

    fun getTransactions() {
        viewModelScope.launch {
            _state.value = TransactionsState.IsLoading(true)

            try {

                val response = withContext(Dispatchers.IO) {
                    requestTransactions()
                }

                response.fold(
                    { error ->
                        _state.value = TransactionsState.Error(error.toString())
                    },
                    { transactionsResponse ->
                        _state.value = TransactionsState.Success(transactionsResponse.data)
                    }
                )

            } catch (e: Exception) {
                _state.value = TransactionsState.Error("Catch: " + e.message.toString())
            } finally {
                _state.value = TransactionsState.IsLoading(false)
                }
            }
        }

    sealed class TransactionsState {
        object Init : TransactionsViewModel.TransactionsState()
        data class IsLoading(val isLoading: Boolean) : TransactionsViewModel.TransactionsState()
        data class Error(val message: String) : TransactionsViewModel.TransactionsState()
        data class Success(val transactions: MutableList<TransactionModel>) : TransactionsViewModel.TransactionsState()
    }
}


