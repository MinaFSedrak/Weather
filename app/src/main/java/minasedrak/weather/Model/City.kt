package minasedrak.weather.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val weather:List<Weather>,
    val main:Main,
    val wind:Wind,
    val name:String = ""
)
