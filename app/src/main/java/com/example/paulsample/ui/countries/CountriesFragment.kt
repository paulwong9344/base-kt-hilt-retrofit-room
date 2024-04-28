package com.example.paulsample.ui.countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.paulsample.base.BaseFragment
import com.example.paulsample.databinding.FragmentCountriesBinding
import com.example.paulsample.repository.model.countries.Country
import com.example.paulsample.viewmodel.countries.CountriesAdapter
import com.example.paulsample.viewmodel.countries.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : BaseFragment<FragmentCountriesBinding>() {

    private val countriesViewModel: CountriesViewModel by activityViewModels()
    private lateinit var countriesAdapter: CountriesAdapter
    private var listOfCountries = ArrayList<Country>()

    /**
     * Create Binding
     */
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCountriesBinding = FragmentCountriesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializing()
    }

    private fun initializing() {
        binding?.recyclerviewCountries?.layoutManager = GridLayoutManager(context, 3)
        countriesAdapter = CountriesAdapter(listOfCountries)
        binding?.recyclerviewCountries?.adapter = countriesAdapter

        countriesAdapter.onCountryClicked = {
            findNavController().navigate(CountriesFragmentDirections.toNewsFragment(it.countryKey!!))
        }

        observeCountries()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeCountries() {
        countriesViewModel.getCountries().observe(viewLifecycleOwner) {
            it?.let {
                listOfCountries.clear()
                listOfCountries.addAll(it)
                countriesAdapter.notifyDataSetChanged()
            }
        }
    }
}
