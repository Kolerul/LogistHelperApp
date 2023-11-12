package com.example.logisthelperapp.di

import com.example.logisthelperapp.presentation.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(): AppComponent
    }

    fun viewModelFactory(): ViewModelFactory
}