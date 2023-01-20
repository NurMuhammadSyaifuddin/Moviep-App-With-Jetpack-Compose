package com.project.submissionakhir

import android.app.Application
import com.project.submissionakhir.core.di.networkModule
import com.project.submissionakhir.core.di.repositoryModule
import com.project.submissionakhir.di.useCaseModule
import com.project.submissionakhir.di.viewModelModule
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}