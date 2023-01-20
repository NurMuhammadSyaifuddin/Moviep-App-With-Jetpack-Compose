package com.project.submissionakhir.di

import com.project.submissionakhir.core.domain.usecase.Interactor
import com.project.submissionakhir.core.domain.usecase.UseCase
import com.project.submissionakhir.ui.screen.movie.MovieViewModel
import com.project.submissionakhir.ui.screen.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UseCase> { Interactor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
}