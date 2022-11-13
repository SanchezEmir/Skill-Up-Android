package com.Alkemy.alkemybankbase.ui.fragments.transactions

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

    private val _state = MutableLiveData<TransactionsViewModel.TransactionsState>(TransactionsViewModel.TransactionsState.Init)
    val state: MutableLiveData<TransactionsViewModel.TransactionsState> get() = _state

    fun getTransactions() {
        viewModelScope.launch {
            _state.value = TransactionsViewModel.TransactionsState.IsLoading(true)

            try {

                val response = withContext(Dispatchers.IO) {
                    requestTransactions()
                }

                response.fold(
                    { error ->
                        _state.value = TransactionsViewModel.TransactionsState.Error(error.toString())
                    },
                    { transactionsResponse ->
                        _state.value = TransactionsViewModel.TransactionsState.Success(transactionsResponse.data)
                    }
                )

            } catch (e: Exception) {
                _state.value = TransactionsViewModel.TransactionsState.Error("Catch: " + e.message.toString())
            } finally {
                _state.value = TransactionsViewModel.TransactionsState.IsLoading(false)
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


