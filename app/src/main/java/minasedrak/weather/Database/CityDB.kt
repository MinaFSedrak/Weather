package minasedrak.weather.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import minasedrak.weather.Model.City

@Database(
    entities = [City::class],
    version = 1
)
@TypeConverters(CustomTypeConverter::class)
abstract class CityDB: RoomDatabase() {
    abstract fun getCityDAO(): CityDAO
}