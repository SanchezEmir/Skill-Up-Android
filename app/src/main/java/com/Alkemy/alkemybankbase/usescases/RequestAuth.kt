package com.Alkemy.alkemybankbase.usescases

import com.Alkemy.alkemybankbase.data.repository.UserRepository
import javax.inject.Inject

class RequestAuth @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(email:String, password:String) = userRepository.loginUser(email,password)

}