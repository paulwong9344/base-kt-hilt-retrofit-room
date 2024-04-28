package com.example.paulsample.viewmodel.countries

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paulsample.databinding.CellCountryListBinding
import com.example.paulsample.repository.model.countries.Country
import com.example.paulsample.utils.extensions.load

class CountriesAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    var onCountryClicked: ((Country) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesAdapter.ViewHolder {
        val itemBinding = CellCountryListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CountriesAdapter.ViewHolder, position: Int) {
        val country = countries[position]
        holder.bindView(country)
    }

    override fun getItemCount(): Int = countries.size

    inner class ViewHolder(private val itemBinding: CellCountryListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                onCountryClicked?.invoke(countries[adapterPosition])
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + itemBinding.tvCountryName.text + "'"
        }

        fun bindView(country: Country) {
            itemBinding.tvCountryName.text = country.displayName
            itemBinding.ivCountryImage.load(
                itemBinding.root.context,
                Uri.parse(country.countryFagUrl).toString()
            )
        }
    }
}
