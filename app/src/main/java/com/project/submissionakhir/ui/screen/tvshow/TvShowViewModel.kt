package com.project.submissionakhir.ui.screen.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.submissionakhir.core.data.Resource
import com.project.submissionakhir.core.domain.model.TvShow
import com.project.submissionakhir.core.domain.usecase.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TvShowViewModel(private val useCase: UseCase): ViewModel() {

    private val _uiState: MutableStateFlow<Resource<List<TvShow>>> = MutableStateFlow(Resource.Loading())
    val uiState: StateFlow<Resource<List<TvShow>>> get() = _uiState

    fun getAiringTodayTvShow() {
        viewModelScope.launch {
            useCase.getAiringTodayTvShow()
                .catch {
                    _uiState.value = Resource.Error(it.message.toString())
                }
                .collect { resource ->
                    when(resource){
                        is Resource.Loading -> _uiState.value = Resource.Loading()
                        is Resource.Success -> _uiState.value = Resource.Success(resource.data)
                        is Resource.Error -> _uiState.value = Resource.Error(resource.message.toString())
                    }
                }
        }
    }

}