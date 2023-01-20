package com.project.submissionakhir.core.data.remote

import com.project.submissionakhir.core.data.remote.network.ApiResponse
import com.project.submissionakhir.core.data.remote.network.ApiService
import com.project.submissionakhir.core.data.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getPopularMovies() =
        channelFlow {
            try {
                val response = apiService.getPopularMovie()
                val data = response.results
                if (data.isNotEmpty()) send(ApiResponse.Success(data))
                else send(ApiResponse.Empty)
            }catch (e: Exception){
                e.printStackTrace()
                send(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getAiringTodayTvShow() =
        channelFlow {
            try {
                val response = apiService.getAiringTodayTvShow()
                val data = response.results
                if (data.isNotEmpty()) send(ApiResponse.Success(data))
                else send(ApiResponse.Empty)
            }catch (e: Exception){
                e.printStackTrace()
                send(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}