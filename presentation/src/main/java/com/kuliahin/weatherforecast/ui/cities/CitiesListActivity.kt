package com.kuliahin.weatherforecast.ui.cities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kuliahin.weatherforecast.databinding.ActivityCitiesListBinding
import com.kuliahin.weatherforecast.ui.adapters.CitiesAdapter

class CitiesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCitiesListBinding

    private val adapter =
        CitiesAdapter {
            startActivity(Intent(this, CityWeather::class.java))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCitiesListBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUI()
    }

    private fun initUI() {
        binding.rvCities.adapter = adapter

        adapter.submitList(listOf(CityWeather(1, "Kharkiv", 10), CityWeather(2, "Uzhhorod", 12)))
    }
}
