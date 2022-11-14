package com.Alkemy.alkemybankbase.ui.fragments.chargebalance

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentChargeBalanceBinding

class ChargeBalanceFragment : Fragment(R.layout.fragment_charge_balance) {

    private lateinit var binding: FragmentChargeBalanceBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChargeBalanceBinding.bind(view)

        events()
        setupObservers()

    }

    private fun setupObservers() {

    }

    private fun events() {

    }

}