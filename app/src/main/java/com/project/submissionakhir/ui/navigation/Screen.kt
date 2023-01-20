package com.project.submissionakhir.ui.navigation

sealed class Screen(val route: String) {
    object Movie : Screen("movie")
    object TvShow : Screen("tvshow")
    object Profile : Screen("profile")
    object DetailMovie : Screen("movie/{name}/{releaseDate}/{overview}/{backdrop}/{poster}") {
        fun createRoute(
            name: String,
            releaseDate: String,
            overview: String?,
            backdrop: String?,
            poster: String?
        ) = "movie/$name/$releaseDate/$overview/$backdrop/$poster"
    }

    object DetailTvShow : Screen("tvshow/{title}/{firstAirDate}/{overview}/{backdrop}/{poster}") {
        fun createRoute(
            title: String?,
            firstAirDate: String?,
            overview: String?,
            backdrop: String?,
            poster: String?
        ) = "tvshow/$title/$firstAirDate/$overview/$backdrop/$poster"
    }
}