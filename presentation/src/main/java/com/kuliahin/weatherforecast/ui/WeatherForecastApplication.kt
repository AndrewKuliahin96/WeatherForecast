package com.kuliahin.weatherforecast.ui

import android.app.Application
import com.kuliahin.weatherforecast.data.di.dataModule
import com.kuliahin.weatherforecast.domain.di.domainModule
import com.kuliahin.weatherforecast.ui.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class WeatherForecastApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@WeatherForecastApplication)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}
