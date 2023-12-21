package minasedrak.weather.Database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import minasedrak.weather.Model.Main
import minasedrak.weather.Model.Weather
import minasedrak.weather.Model.Wind

class CustomTypeConverter {

    // WeatherList Converter
    @TypeConverter
    fun fromListWeather(weatherList: List<Weather>): String {
        return Gson().toJson(weatherList)
    }

    @TypeConverter
    fun toListWeather(json: String): List<Weather> {
        val type = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(json, type)
    }

    // Main Converter
    @TypeConverter
    fun fromMain(main: Main): String {
        return Gson().toJson(main)
    }

    @TypeConverter
    fun toMain(json: String): Main {
        return Gson().fromJson(json, Main::class.java)
    }

    // Wind Converter
    @TypeConverter
    fun fromWind(wind: Wind): String {
        return Gson().toJson(wind)
    }

    @TypeConverter
    fun toWind(json: String): Wind {
        return Gson().fromJson(json, Wind::class.java)
    }
}