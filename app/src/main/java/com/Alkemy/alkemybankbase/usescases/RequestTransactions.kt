package com.Alkemy.alkemybankbase.usescases

import com.Alkemy.alkemybankbase.data.repository.UserRepository
import javax.inject.Inject

class RequestTransactions @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getTransactions()
}