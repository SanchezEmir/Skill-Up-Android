package com.Alkemy.alkemybankbase.ui.fragments.expenses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.model.ExpenseRequest
import com.Alkemy.alkemybankbase.databinding.FragmentExpensesBinding
import com.Alkemy.alkemybankbase.utils.DatePickerModal
import com.Alkemy.alkemybankbase.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExpensesFragment : Fragment(R.layout.fragment_expenses) {

    private lateinit var binding: FragmentExpensesBinding

    private val viewModel: ExpensesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentExpensesBinding.bind(view)

        events()
        setupObservers()

    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{ state ->
                    updateUI(state)
                }
            }
        }
    }

    private fun updateUI(state: ExpensesViewModel.ExpensesState) {
        when(state){
            ExpensesViewModel.ExpensesState.Init -> Unit
            is ExpensesViewModel.ExpensesState.Error -> requireContext().toast(state.message)
            is ExpensesViewModel.ExpensesState.IsLoading -> handleLoading(state.isLoading)
            is ExpensesViewModel.ExpensesState.Success -> requireContext().toast(state.response)
        }
    }

    private fun handleLoading(isLoading: Boolean) {


    }

    private fun events() = with(binding) {

        edtDate.setOnClickListener {
            showDatePickerDialog()
        }

        btnRegisterExpense.setOnClickListener {

            val amount = binding.edtamount.text.toString().toInt()
            val concept = binding.edtConcept.text.toString()
            var date = binding.edtDate.text.toString()

//            date = date.replace("/", "-")
            date = "2021-09-09"
            val request = ExpenseRequest(amount,concept, date)

            viewModel.saveExpense(request)

        }

    }

    private fun showDatePickerDialog() {

        val datePicker = DatePickerModal { day, month, year -> onDateSelected(year, month, day) }
        datePicker.show(requireActivity().supportFragmentManager, "datePicker")

    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        val date = "$year-$month-$day"
        binding.edtDate.setText(date)
    }


}