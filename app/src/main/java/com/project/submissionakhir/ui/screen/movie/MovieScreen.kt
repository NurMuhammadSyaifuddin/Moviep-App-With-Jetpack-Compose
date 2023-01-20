package com.project.submissionakhir.ui.screen.movie

import com.project.submissionakhir.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.submissionakhir.core.data.Resource
import com.project.submissionakhir.core.domain.model.Movie
import com.project.submissionakhir.core.domain.model.TvShow
import com.project.submissionakhir.ui.component.MovieOrTvShowItem
import com.project.submissionakhir.utils.BASE_URL_API_IMAGE
import com.project.submissionakhir.utils.POSTER_SIZE_W185
import com.project.submissionakhir.utils.encodedUrl

@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    navigateToDetail: (String, String, String?, String?, String?) -> Unit
) {
    viewModel.uiState.collectAsState(
        initial = Resource.Loading()
    ).value.let { resource ->
        when (resource) {
            is Resource.Loading -> {
                viewModel.getPopularMovies()
                LoadingContent()
            }
            is Resource.Success -> MovieOrTvShowContent(
                data = resource.data as List<Movie>,
                navigateToDetail = navigateToDetail
            )
            is Resource.Error -> ErrorContent(errorMessage = resource.message.toString())
        }
    }
}

@Composable
fun <T> MovieOrTvShowContent(
    modifier: Modifier = Modifier,
    data: List<T>,
    navigateToDetail: (String, String, String?, String?, String?) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(data, key = {
            when(it){
                is Movie -> it.id
                is TvShow -> it.id
                else -> {}
            }
        }) { data ->
            when (data) {
                is Movie -> MovieOrTvShowItem(title = data.title.toString(),
                    image = "$BASE_URL_API_IMAGE$POSTER_SIZE_W185${data.posterPath}",
                    modifier = modifier.clickable {
                        navigateToDetail(
                            data.title.toString(),
                            data.releaseDate.toString(),
                            if (data.overview.isNullOrBlank()) "No Description" else data.overview,
                            if (data.backdropPath.isNullOrBlank()) "-" else data.backdropPath.encodedUrl(),
                            if (data.posterPath.isNullOrBlank()) "-" else data.posterPath.encodedUrl()
                        )
                    })
                is TvShow -> MovieOrTvShowItem(title = data.name.toString(),
                    image = "$BASE_URL_API_IMAGE$POSTER_SIZE_W185${data.posterPath}",
                    modifier = modifier.clickable {
                        navigateToDetail(
                            if (data.name.isNullOrBlank()) "-" else data.name,
                            if (data.firstAirDate.isNullOrBlank()) "-" else data.firstAirDate,
                            if (data.overview.isNullOrBlank()) "No Description" else data.overview,
                            if (data.backdropPath.isNullOrBlank()) "-" else data.backdropPath.encodedUrl(),
                            if (data.posterPath.isNullOrBlank()) "-" else data.posterPath.encodedUrl()
                        )
                    })
            }

        }
    }
}

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = errorMessage,
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}