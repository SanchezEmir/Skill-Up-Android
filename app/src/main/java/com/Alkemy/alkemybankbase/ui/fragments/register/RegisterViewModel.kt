package com.Alkemy.alkemybankbase.ui.fragments.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Alkemy.alkemybankbase.data.model.ErrorResponse
import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.data.model.UserRegisterResponse
import com.Alkemy.alkemybankbase.domain.User
import com.Alkemy.alkemybankbase.usescases.RegisterUser
import com.Alkemy.alkemybankbase.usescases.RequestAuth
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUser: RegisterUser) : ViewModel() {

    private val _state = MutableLiveData<RegisterUserState>(RegisterUserState.Init)
    val state: MutableLiveData<RegisterUserState> get() = _state

    fun createUsuario(request: UserRegisterRequest) {

        viewModelScope.launch {

            _state.value = RegisterUserState.IsLoading(true)

            try {

                val response = withContext(Dispatchers.IO) {
                    registerUser(request)
                }

                response.fold(
                    { error ->
//                        val responseError = response.errorBody()?.string()
//                        val gson = Gson()
//                        val responseErrorObj: ErrorResponse = gson.fromJson(responseError, ErrorResponse::class.java)
//                        Log.i("ERROR", responseError.toString())
//                        responseErrorObj?.let { error ->
//                            _state.value = RegisterUserState.Error(error.error)
//                        }
                        _state.value = RegisterUserState.Error(error.toString())
                    },
                    { user ->
                        _state.value = RegisterUserState.SuccessRegister(user)
                    }
                )

            } catch (e: Exception) {
                _state.value = RegisterUserState.Error("Catch: " + e.message.toString())
            } finally {
                _state.value = RegisterUserState.IsLoading(false)
            }

            /*try {
                val response = withContext(Dispatchers.IO) {
                    Api.build().createUser(request)
                }
                if (response.code() == 201) {
                    val responseData = response.body()
                    responseData?.let { user ->
                        _state.value = RegisterUserState.SuccessRegister(user)
                    }
                } else {
                    val responseError = response.errorBody()?.string()
                    val gson = Gson()
                    val responseErrorObj: ErrorResponse = gson.fromJson(responseError, ErrorResponse::class.java)
                    Log.i("ERROR", responseError.toString())
                    responseErrorObj?.let { error ->
                        _state.value = RegisterUserState.Error(error.error)
                    }
                }
            } catch (e: Exception) {
                _state.value = RegisterUserState.Error("Catch: "+e.message.toString())
            } finally {
                _state.value = RegisterUserState.IsLoading(false)
            }*/

        }

    }

    sealed class RegisterUserState {
        object Init : RegisterUserState()
        data class IsLoading(val isLoading: Boolean) : RegisterUserState()
        data class Error(val message: String) : RegisterUserState()
        data class SuccessRegister(val user: User) : RegisterUserState()
    }

}