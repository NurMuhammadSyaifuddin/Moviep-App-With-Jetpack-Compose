package com.project.submissionakhir.ui.screen.tvshow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.project.submissionakhir.core.data.Resource
import com.project.submissionakhir.core.domain.model.TvShow
import com.project.submissionakhir.ui.screen.movie.*

@Composable
fun TvShowScreen(
    viewModel: TvShowViewModel,
    navigateToDetail: (String, String, String?, String?, String?) -> Unit
) {
    viewModel.uiState.collectAsState(
        initial = Resource.Loading()
    ).value.let { resource ->
        when(resource){
            is Resource.Loading -> {
                viewModel.getAiringTodayTvShow()
                LoadingContent()
            }
            is Resource.Success -> MovieOrTvShowContent(
                data = resource.data as List<TvShow>,
                navigateToDetail = navigateToDetail
            )
            is Resource.Error -> ErrorContent(errorMessage = resource.message.toString())
        }
    }
}