package minasedrak.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStarted
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import minasedrak.weather.ViewModel.WeatherVM
import minasedrak.weather.databinding.ActivityMainBinding
import kotlin.math.roundToInt

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherVM: WeatherVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val mainView = binding.root
        setContentView(mainView)
        initSearchViewListener()

        // Bind UI data on collect a new weather response
        lifecycleScope.launch {
            weatherVM.weatherResponse.collect {response ->
                Glide.with(this@MainActivity).load("https://openweathermap.org/img/w/${response.weather[0].icon}.png").into(binding.image)
                binding.description.text = response.weather[0].description
                binding.name.text = response.name
                binding.degree.text = response.wind.deg.toString()
                binding.speed.text = response.wind.speed.toString()
                binding.temp.text = response.main.temp.toString()
                binding.humidity.text = response.main.humidity.toString()
            }
        }
    }

    // SearchView Listener
    private fun initSearchViewListener() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { weatherVM.setSearchQuery(it) }
                    Log.d("MainActivity", "onQueryTextChange: $query")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
    }
}