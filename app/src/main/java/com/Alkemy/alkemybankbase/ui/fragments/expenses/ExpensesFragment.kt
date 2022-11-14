package com.Alkemy.alkemybankbase.ui.fragments.expenses

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.model.ExpenseRequest
import com.Alkemy.alkemybankbase.databinding.FragmentExpensesBinding
import com.Alkemy.alkemybankbase.utils.DatePickerModal
import com.Alkemy.alkemybankbase.utils.controlEmailAndPassword
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
            is ExpensesViewModel.ExpensesState.Error -> showError(state.message)
            is ExpensesViewModel.ExpensesState.IsLoading -> handleLoading(state.isLoading)
            is ExpensesViewModel.ExpensesState.Success -> showSucces(state.response)
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    private fun showError(error: String) {
        val dialog: AlertDialog =
            AlertDialog.Builder(context).setMessage(error).setTitle("Error al ingresar un gasto")
                .setPositiveButton(
                    "OK"
                ) { _, _ -> }
                .create()
        dialog.show()
        binding.tilAmount.error = getString(R.string.fragment_input_error)
        binding.tilConcept.error = getString(R.string.fragment_input_error)
        binding.tilDate.error = getString(R.string.fragment_input_error)
    }

    private fun showSucces(succes: String) {
        val dialog: AlertDialog =
            AlertDialog.Builder(context).setMessage(succes).setTitle("Registro exitoso")
                .setPositiveButton(
                    "OK"
                ) { _, _ -> }
                .create()
        dialog.show()
    }

    private fun events() = with(binding) {

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

                val amount =edtamount.text
                val concept = edtConcept.text
                val date = edtDate.text.toString()

                // Validates amount, concept and date when inputs are changed and enables
                // Register Expense Button when all fields are validated
                btnRegisterExpense.isEnabled =
                    amount.toString() != "0" && !amount.isNullOrEmpty() && !concept.isNullOrEmpty() && !date.isNullOrEmpty()

                tilAmount.isErrorEnabled = false
                tilConcept.isErrorEnabled = false
                tilDate.isErrorEnabled = false

            }
        }

        edtamount.addTextChangedListener(textWatcher)
        edtConcept.addTextChangedListener(textWatcher)
        edtDate.addTextChangedListener(textWatcher)

        edtDate.setOnClickListener {
            showDatePickerDialog()
        }

        btnRegisterExpense.setOnClickListener {

            val amount = edtamount.text.toString().toInt()
            val concept = edtConcept.text.toString()
            var date = edtDate.text.toString()

            date = date.replace("/", "-")
//            date = "2021-09-09"
            val request = ExpenseRequest(amount,concept, date)

            viewModel.saveExpense(request)

            edtamount.text?.clear()
            edtConcept.text?.clear()
            edtDate.text?.clear()


        }

    }

    private fun showDatePickerDialog() {

        val datePicker = DatePickerModal { day, month, year -> onDateSelected(year, month, day) }
        datePicker.show(requireActivity().supportFragmentManager, "datePicker")

    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        val date = "$year-${month+1}-$day"
        binding.edtDate.setText(date)
    }


}