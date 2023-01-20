package com.project.submissionakhir.core.data.repository

import com.project.submissionakhir.core.data.Resource
import com.project.submissionakhir.core.data.remote.RemoteDataSource
import com.project.submissionakhir.core.data.remote.network.ApiResponse
import com.project.submissionakhir.core.domain.model.Movie
import com.project.submissionakhir.core.domain.model.TvShow
import com.project.submissionakhir.core.domain.repository.IRepository
import com.project.submissionakhir.utils.mapMovieResponseToDomainResponse
import com.project.submissionakhir.utils.mapTvShowResponseToDomainResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class Repository(
    private val remoteDataSource: RemoteDataSource
): IRepository {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading())
            when(val apiResponse = remoteDataSource.getPopularMovies().first()){
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.map { mapMovieResponseToDomainResponse(it) }))
                is ApiResponse.Empty -> emit(Resource.Success(null))
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }

    override fun getAiringTodayTvShow(): Flow<Resource<List<TvShow>>> =
        flow {
            emit(Resource.Loading())
            when(val apiResponse = remoteDataSource.getAiringTodayTvShow().first()){
                is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.map { mapTvShowResponseToDomainResponse(it) }))
                is ApiResponse.Empty -> emit(Resource.Success(null))
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.errorMessage))
            }
        }
}