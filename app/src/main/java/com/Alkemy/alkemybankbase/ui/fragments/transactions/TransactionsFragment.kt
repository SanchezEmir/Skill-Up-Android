package com.Alkemy.alkemybankbase.ui.fragments.transactions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentLoginBinding
import com.Alkemy.alkemybankbase.databinding.FragmentTransactionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragment : Fragment() {

    private lateinit var binding: FragmentTransactionsBinding

    private val transactionsViewModel: TransactionsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transactionsViewModel.getTransactions()

    }
}