package com.example.logisthelperapp

import android.app.Application
import com.example.logisthelperapp.di.AppComponent
import com.example.logisthelperapp.di.DaggerAppComponent

class LogistHelperApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create()
    }
}