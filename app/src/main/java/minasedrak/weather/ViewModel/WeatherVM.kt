package minasedrak.weather.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import minasedrak.weather.Model.City
import minasedrak.weather.Model.Main
import minasedrak.weather.Model.Weather
import minasedrak.weather.Model.Wind
import minasedrak.weather.Repository.WeatherRepo
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class WeatherVM @Inject constructor(private val weatherRepo: WeatherRepo): ViewModel() {
    private val searchChannel = MutableSharedFlow<String>(1)
    val defaultWeatherResponse = City(null,listOf(Weather()), Main(), Wind())
    val weatherResponse: MutableStateFlow<City> = MutableStateFlow(defaultWeatherResponse)

    init {
        getCityData()
    }

    private fun getCityData() {
        viewModelScope.launch {
            searchChannel
                .flatMapLatest { search ->
                    weatherRepo.getCityData(search)
                }.catch { e ->
                    weatherResponse.value = defaultWeatherResponse
                    Log.d("WeatherVM Catch GetCityData:", "${e.message}")
                }.collect { city ->
                    weatherResponse.value = city
                }

        }
    }

    fun setSearchQuery(search: String) {
        searchChannel.tryEmit(search)
    }
}