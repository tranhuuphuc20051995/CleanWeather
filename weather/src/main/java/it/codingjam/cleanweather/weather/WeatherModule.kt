package it.codingjam.cleanweather.weather

import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.cleanweather.domain.TemperatureRepository
import javax.inject.Scope

@Scope
internal annotation class WeatherSingleton

interface WeatherComponent {
    val temperatureRepository: TemperatureRepository
}

@Component(
        modules = [WeatherModule::class],
        dependencies = [WeatherDependencies::class]
)
@WeatherSingleton
interface WeatherComponentImpl : WeatherComponent {

    @Component.Factory
    interface Factory {
        fun create(weatherDependencies: WeatherDependencies): WeatherComponent
    }
}

interface WeatherDependencies {
    val weatherApi: WeatherApi
}

@Module
internal class WeatherModule {

    @WeatherSingleton
    @Provides
    fun provideTemperatureRepository(impl: OpenWeatherTemperatureRepository): TemperatureRepository = impl
}
