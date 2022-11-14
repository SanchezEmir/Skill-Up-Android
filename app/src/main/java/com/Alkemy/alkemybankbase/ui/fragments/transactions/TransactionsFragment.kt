package com.Alkemy.alkemybankbase.ui.fragments.transactions

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Alkemy.alkemybankbase.MenuMainHostActivity
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentLoginBinding
import com.Alkemy.alkemybankbase.databinding.FragmentTransactionsBinding
import com.Alkemy.alkemybankbase.ui.adapters.LastMovementsAdapter
import com.Alkemy.alkemybankbase.ui.fragments.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragment : Fragment(R.layout.fragment_transactions) {

    private lateinit var binding: FragmentTransactionsBinding

    private val transactionsViewModel: TransactionsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTransactionsBinding.bind(view)

        transactionsViewModel.getTransactions()

        setupObservers()
    }

    private fun setupObservers() {
        transactionsViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                TransactionsViewModel.TransactionsState.Init -> Unit
                is TransactionsViewModel.TransactionsState.Error -> showError(state.rawResponse)
                is TransactionsViewModel.TransactionsState.IsLoading -> showProgress(state.isLoading)
                is TransactionsViewModel.TransactionsState.Success -> {

                    val adapter = LastMovementsAdapter()

                    binding.rvListaMovimientos.layoutManager = LinearLayoutManager(requireContext())
                    val recyclerView: RecyclerView = binding.rvListaMovimientos
                    recyclerView.adapter = adapter

                    adapter.lastMovementsAdapter(state.transactions,requireContext())
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    // Show error alert dialog if login fails
    private fun showError(error: String) {
        val dialog: AlertDialog =
            AlertDialog.Builder(context).setMessage(error).setTitle("Invalid user or password")
                .setNeutralButton(
                    "dissmiss"
                ) { _, _ -> }
                .create()
        dialog.show()
    }

    // Show progress indicator while loading
    private fun showProgress(visibility: Boolean) {
        // TODO Show Pregress Indicator
    }
}