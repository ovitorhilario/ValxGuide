package com.example.valxguide.di.modules

import com.example.valxguide.data.api.ValorantService
import com.example.valxguide.data.repository.ValorantRepositoryImpl
import com.example.valxguide.ui.main.activity.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var AppModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://valorant-api.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ValorantService::class.java)
    }

    factory {
        ValorantRepositoryImpl(valorantService = get())
    }

    viewModel {
        MainViewModel(repository = get<ValorantRepositoryImpl>())
    }
}