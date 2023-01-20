package com.project.submissionakhir.utils

import com.project.submissionakhir.core.data.remote.response.MovieResponse
import com.project.submissionakhir.core.data.remote.response.TvShowResponse
import com.project.submissionakhir.core.domain.model.Movie
import com.project.submissionakhir.core.domain.model.TvShow
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val BASE_URL_API_IMAGE = "https://image.tmdb.org/t/p/"
const val POSTER_SIZE_W185 = "w185"
const val POSTER_SIZE_W780 = "w780"

fun mapMovieResponseToDomainResponse(input: MovieResponse) =
    Movie(
        input.id.toLong(),
        input.title,
        input.overview,
        input.posterPath,
        input.backdropPath,
        input.releaseDate
    )

fun mapTvShowResponseToDomainResponse(input: TvShowResponse) =
    TvShow(
        input.id.toLong(),
        input.name,
        input.overview,
        input.posterPath,
        input.backdropPath,
        input.firstAirDate
    )

fun String?.encodedUrl(): String =
    URLEncoder.encode(this, StandardCharsets.UTF_8.toString())