package minasedrak.weather.Network

import minasedrak.weather.Model.City
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {

    suspend fun getCityData(city:String,appId:String): City = apiService.getCityData(city,appId)

}