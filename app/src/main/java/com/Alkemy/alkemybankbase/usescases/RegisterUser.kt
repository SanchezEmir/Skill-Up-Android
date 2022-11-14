package com.Alkemy.alkemybankbase.usescases

import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.data.repository.UserRepository
import javax.inject.Inject

class RegisterUser @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(request: UserRegisterRequest) = userRepository.createUser(request)

}