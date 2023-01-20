package com.project.submissionakhir.core.domain.model

data class Movie(
    val id: Long,

    val title: String?,

    val overview: String?,

    val posterPath: String?,

    val backdropPath: String?,

    val releaseDate: String?
)
