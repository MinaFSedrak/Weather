package minasedrak.weather.DI

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import minasedrak.weather.Database.CityDB
import minasedrak.weather.Network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesCitiesRoomDB(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        CityDB::class.java,
        "weatherDB"
    ).build()

    @Singleton
    @Provides
    fun providesCityDAO(cityDB: CityDB) = cityDB.getCityDAO()

    @Provides
    fun providesWeatherBaseURL(): String = "https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun providesRetrofitBuilder(weatherBaseURL:String): Retrofit = Retrofit.Builder()
        .baseUrl(weatherBaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}