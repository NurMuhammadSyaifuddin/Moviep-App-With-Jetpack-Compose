package com.project.submissionakhir.core.domain.usecase

import com.project.submissionakhir.core.data.Resource
import com.project.submissionakhir.core.domain.model.Movie
import com.project.submissionakhir.core.domain.model.TvShow
import com.project.submissionakhir.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class Interactor(private val repository: IRepository): UseCase {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = repository.getPopularMovies()

    override fun getAiringTodayTvShow(): Flow<Resource<List<TvShow>>> = repository.getAiringTodayTvShow()
}