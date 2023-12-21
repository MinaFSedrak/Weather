package minasedrak.weather.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import minasedrak.weather.Model.City

@Dao
interface CityDAO {

    @Query("SELECT * from cities")
    fun getAllCities(): List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: City)

    @Delete
    fun deleteCity(city: City)
}