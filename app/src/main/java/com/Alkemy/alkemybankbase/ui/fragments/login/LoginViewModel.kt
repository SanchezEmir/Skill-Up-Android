package com.Alkemy.alkemybankbase.ui.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Alkemy.alkemybankbase.domain.UserLogin
import com.Alkemy.alkemybankbase.usescases.RequestAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject
constructor(private val requestAuth: RequestAuth) : ViewModel() {

    private val _state = MutableLiveData<LoginState>(LoginState.Init)
    val state: LiveData<LoginState> get() = _state

    fun auth(email: String, password: String) {

        viewModelScope.launch {

            _state.value = LoginState.IsLoading(true)

            try {
                val response = withContext(Dispatchers.IO) {
                    requestAuth(email, password)
                }

                response.fold(
                    { error ->
                        _state.value = LoginState.Error(error.toString())
                    },
                    { user ->
                        _state.value = LoginState.Success(user)
                    }
                )

                /*if (response.isSuccessful) {
                    val response = response.body()
                    response?.let {
                        if (it.accessToken != null) {
                            _state.value = LoginState.Success(UserRemoteResponse(it.accessToken))
                            userPreferences.saveAuthToken(it.accessToken)
                        } else {
                            _state.value = LoginState.Error(it.error!!)
                        }
                    }
                } else {
                    _state.value = LoginState.Error(response.toString())
                }*/
            } catch (ex: Exception) {
                _state.value = LoginState.Error(ex.message.toString())
            } finally {
                _state.value = LoginState.IsLoading(false)
            }
        }
    }

    sealed class LoginState {

        object Init : LoginState()
        data class IsLoading(val isLoading: Boolean) : LoginState()
        data class Error(val rawResponse: String) : LoginState()
        data class Success(val user: UserLogin) : LoginState()

    }
}
