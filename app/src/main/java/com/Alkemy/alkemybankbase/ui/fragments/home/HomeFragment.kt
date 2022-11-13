package com.Alkemy.alkemybankbase.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.databinding.FragmentHomeBinding
import com.Alkemy.alkemybankbase.ui.adapters.LastMovementsAdapter


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    //private lateinit var homeAdapter: LastMovementsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        //binding.recyclerTransactions.adapter = homeAdapter
    }


}