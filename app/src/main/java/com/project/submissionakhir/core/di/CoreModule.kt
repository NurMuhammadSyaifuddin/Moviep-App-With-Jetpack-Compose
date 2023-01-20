package com.project.submissionakhir.core.di

import com.project.submissionakhir.BuildConfig
import com.project.submissionakhir.core.data.remote.RemoteDataSource
import com.project.submissionakhir.core.data.remote.network.ApiService
import com.project.submissionakhir.core.data.repository.Repository
import com.project.submissionakhir.core.domain.repository.IRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<IRepository> {
        Repository(
            get()
        )
    }
}