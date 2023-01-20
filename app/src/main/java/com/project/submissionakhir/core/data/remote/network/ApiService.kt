package com.project.submissionakhir.core.data.remote.network

import com.project.submissionakhir.BuildConfig
import com.project.submissionakhir.core.data.remote.response.ListResponse
import com.project.submissionakhir.core.data.remote.response.MovieResponse
import com.project.submissionakhir.core.data.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String = BuildConfig.API_KEY): ListResponse<MovieResponse>

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShow(@Query("api_key") apiKey: String = BuildConfig.API_KEY): ListResponse<TvShowResponse>

}