package com.kuliahin.weatherforecast.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kuliahin.weatherforecast.R
import com.kuliahin.weatherforecast.databinding.ViewItemCityBinding
import com.kuliahin.weatherforecast.ui.cities.CityWeather

class CitiesAdapter(private val onCityClickedCallback: (CityWeather) -> Unit) :
    ListAdapter<CityWeather, CitiesAdapter.CitiesViewHolder>(CALLBACK_DIFF) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CitiesViewHolder {
        return CitiesViewHolder(
            ViewItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: CitiesViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    inner class CitiesViewHolder(private val binding: ViewItemCityBinding) :
        ViewHolder(binding.root) {
        fun bind(data: CityWeather) {
            with(binding) {
                tvCity.text = data.cityName
                tvForecast.text =
                    binding.root.context.getString(
                        R.string.temperature_placeholder,
                        data.temperature,
                    )

                binding.root.setOnClickListener {
                    onCityClickedCallback(data)
                }
            }
        }
    }

    companion object {
        private val CALLBACK_DIFF =
            object : ItemCallback<CityWeather>() {
                override fun areItemsTheSame(
                    oldItem: CityWeather,
                    newItem: CityWeather,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CityWeather,
                    newItem: CityWeather,
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
