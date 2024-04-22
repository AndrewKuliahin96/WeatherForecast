package com.kuliahin.weatherforecast.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kuliahin.weatherforecast.R
import com.kuliahin.weatherforecast.ui.adapters.ForecastAdapter.ForecastViewHolder
import com.kuliahin.weatherforecast.databinding.ViewItemDetailedForecastBinding
import com.kuliahin.weatherforecast.ui.cities.DayForecast

class ForecastAdapter(private val onCityClickedCallback: (DayForecast) -> Unit) :
    ListAdapter<DayForecast, ForecastViewHolder>(CALLBACK_DIFF) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ForecastViewHolder {
        return ForecastViewHolder(
            ViewItemDetailedForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: ForecastViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    inner class ForecastViewHolder(private val binding: ViewItemDetailedForecastBinding) :
        ViewHolder(binding.root) {
        fun bind(data: DayForecast) {
            with(binding) {
                tvDate.text = data.date
                tvShortInfo.text = data.info
                tvTemperature.text =
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
            object : ItemCallback<DayForecast>() {
                override fun areItemsTheSame(
                    oldItem: DayForecast,
                    newItem: DayForecast,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: DayForecast,
                    newItem: DayForecast,
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
