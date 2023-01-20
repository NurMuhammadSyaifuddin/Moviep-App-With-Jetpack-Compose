package com.project.submissionakhir.core.domain.usecase

import com.project.submissionakhir.core.data.Resource
import com.project.submissionakhir.core.domain.model.Movie
import com.project.submissionakhir.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface UseCase {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getAiringTodayTvShow(): Flow<Resource<List<TvShow>>>

}