package com.Alkemy.alkemybankbase.ui.fragments.register

import android.R.attr.editable
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.model.UserRegisterRequest
import com.Alkemy.alkemybankbase.databinding.FragmentRegisterBinding
import com.Alkemy.alkemybankbase.utils.controlEmailAndPassword
import com.Alkemy.alkemybankbase.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding : FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)

        init()
        events()
        setupObservers()

    }

    private fun setupObservers() {

        viewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {
                RegisterViewModel.RegisterUserState.Init -> Unit
                is RegisterViewModel.RegisterUserState.Error -> showError(state.message)
                is RegisterViewModel.RegisterUserState.IsLoading -> showProgress(state.isLoading)
                is RegisterViewModel.RegisterUserState.SuccessRegister -> {

                    requireActivity().onBackPressed()
                }
            }
        }
    }

    private fun showError(message: String) {
        val dialog: AlertDialog =
            AlertDialog.Builder(context).setMessage(message).setTitle("Sign up error")
                .setNeutralButton(
                    "retry"
                ) { _, _ -> }
                .create()
        dialog.show()
    }

    private fun showProgress(visibility:Boolean) = with(binding){
        if (visibility) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

    private fun events() = with(binding) {

        include.imgBackHeader.setOnClickListener {
            activity?.onBackPressed()
        }


        val textWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

                val name =edtFirstName.text
                val lastName = edtLastName.text
                val email = edtEmailRegistro.text.toString()
                val password = edtPasswordRegistro.text.toString()
                val confirmPassword = edtPasswordConfirmationRegistro.text.toString()

                // Validates Name, Last Name, Email, Password, Confirmed Password
                // and Terms Acceptance when inputs are changed and enables
                // Sign Up Button when all fields are validated
                btnRegister.isEnabled = controlEmailAndPassword(email,password)
                        && !name.isNullOrEmpty() && !lastName.isNullOrEmpty() && swTerms.isChecked && password == confirmPassword

                // Check that Password and Confirm Password are the same.
                // If they are different shows error
                if (confirmPassword.isNotEmpty() && password.isNotEmpty()) {
                    if (confirmPassword != password) {
                        tilPasswordConfirmation.error = getString(R.string.fragment_register_invalid_password_confirmation)
                    } else {
                        tilPasswordConfirmation.isErrorEnabled = false
                    }
                }
            }
        }

        edtFirstName.addTextChangedListener(textWatcher)
        edtLastName.addTextChangedListener(textWatcher)
        edtEmailRegistro.addTextChangedListener(textWatcher)
        edtPasswordRegistro.addTextChangedListener(textWatcher)
        edtPasswordConfirmationRegistro.addTextChangedListener(textWatcher)

        swTerms.setOnClickListener{
            val name =edtFirstName.text
            val lastName = edtLastName.text
            val email = edtEmailRegistro.text.toString()
            val password = edtPasswordRegistro.text.toString()
            val confirmPassword = edtPasswordConfirmationRegistro.text.toString()

            btnRegister.isEnabled = controlEmailAndPassword(email,password)
                    && !name.isNullOrEmpty() && !lastName.isNullOrEmpty() && swTerms.isChecked && password == confirmPassword
        }


        // Does Register when Sign Up Button is clicked
        btnRegister.setOnClickListener {

            val name = edtFirstName.text.toString()
            val lastName = edtLastName.text.toString()
            val email = edtEmailRegistro.text.toString()
            val password = edtPasswordRegistro.text.toString()

            viewModel.createUsuario(UserRegisterRequest(name, lastName, email, password))

        }

    }

    private fun init() = with(binding) {

        include.tvTitleHeader.text = getString(R.string.fragment_register_title)

    }

}