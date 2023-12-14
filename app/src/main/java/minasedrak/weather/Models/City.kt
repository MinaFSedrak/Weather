package minasedrak.weather.Models

data class City(
    val weather:List<Weather>,
    val main:Main,
    val wind:Wind,
    val name:String = ""
)
