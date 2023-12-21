package minasedrak.weather.Repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import minasedrak.weather.Database.CityDAO
import minasedrak.weather.Model.City
import minasedrak.weather.Network.ApiServiceImp
import javax.inject.Inject

class WeatherRepo @Inject constructor(private val apiServiceImp: ApiServiceImp, private val cityDAO: CityDAO) {

    fun getCityData(city:String):Flow<City> = flow {
        val response = apiServiceImp.getCityData(city, "3809ea75162bdd7cc050817ced9f978c")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()

    fun insertTest(city:City): Flow<String> = flow {
        val city = insertCityToDB(city)
        emit("Inserted Successfully")
    }.flowOn(Dispatchers.IO)
        .conflate()

    fun getAllTest(): Flow<List<City>> = flow {
        val cities = getAllCitiesFromDB()
        emit(cities)
    }.flowOn(Dispatchers.IO)
        .conflate()

    private suspend fun insertCityToDB(city: City) = cityDAO.insertCity(city)
    private suspend fun getAllCitiesFromDB(): List<City> = cityDAO.getAllCities()
}