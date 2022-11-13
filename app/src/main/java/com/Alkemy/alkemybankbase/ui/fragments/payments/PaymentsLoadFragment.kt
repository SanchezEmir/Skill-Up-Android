package com.Alkemy.alkemybankbase.ui.fragments.payments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentPaymentsLoadBinding


class PaymentsLoadFragment : Fragment(R.layout.fragment_payments_load) {

    private lateinit var binding: PaymentsLoadFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaymentsLoadBinding.bind(view)
    }


}