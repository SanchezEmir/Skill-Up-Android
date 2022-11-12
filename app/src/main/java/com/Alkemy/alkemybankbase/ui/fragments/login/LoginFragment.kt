package com.Alkemy.alkemybankbase.ui.fragments.login

import android.content.Intent
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.Alkemy.alkemybankbase.MenuMainHostActivity
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentLoginBinding
import com.Alkemy.alkemybankbase.utils.controlEmailAndPassword
import com.Alkemy.alkemybankbase.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        //Mockup
        //binding.edtEmailLogin.setText("prueba3@alkemy.com")
        //binding.edtPasswordLogin.setText("123456")

        events()
        setupObservers()
    }

    private fun events() = with(binding) {

        // Validates Email and Password when inputs are changed and enables
        // Sign In Button when both fields are validated
        val textWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                btnSingIn.isEnabled = controlEmailAndPassword(edtEmailLogin.text.toString(),edtPasswordLogin.text.toString())
            }
        }
        edtEmailLogin.addTextChangedListener(textWatcher)
        edtPasswordLogin.addTextChangedListener(textWatcher)

        // Does Login when Sign In Button is clicked
        btnSingIn.setOnClickListener {
            val email = binding.edtEmailLogin.text.toString()
            val password = binding.edtPasswordLogin.text.toString()

            viewModel.auth(email, password)
        }

        // Navigates to Register
        tvCreateUser.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    // Sets Up Login State
    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                LoginViewModel.LoginState.Init -> Unit
                is LoginViewModel.LoginState.Error -> showError(state.rawResponse)
                is LoginViewModel.LoginState.IsLoading -> showProgress(state.isLoading)
                is LoginViewModel.LoginState.Success -> {
                    val userRemote = state.user
                    // TODO Navigate to Home
                    requireContext().toast("Token ${userRemote.accessToken}")

                    val intent = Intent(requireContext(), MenuMainHostActivity::class.java)
                    startActivity(intent)

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