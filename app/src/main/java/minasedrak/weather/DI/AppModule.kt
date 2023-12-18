package minasedrak.weather.DI

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import minasedrak.weather.Network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
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