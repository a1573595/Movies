package com.a1573595.movies.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.a1573595.movies.MainApplication
import com.a1573595.movies.data.MovieRepository
import com.a1573595.movies.model.DiscoverMovie
import com.a1573595.movies.model.Movie
import kotlinx.coroutines.launch

sealed interface MovieListUiState {
    data object Loading : MovieListUiState
    data class Success(val list: List<DiscoverMovie>) : MovieListUiState
    data class Error(val throwable: Throwable) : MovieListUiState
}

sealed interface MovieUiState {
    data object Loading : MovieUiState
    data class Success(val data: Movie) : MovieUiState
    data class Error(val throwable: Throwable) : MovieUiState
}

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val movieRepository = application.container.movieRepository
                MovieViewModel(movieRepository = movieRepository)
            }
        }
    }

    var listUiState: MovieListUiState by mutableStateOf(MovieListUiState.Loading)
        private set

    var detailUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    init {
        getDiscoverMovieList()
    }

    private fun getDiscoverMovieList() {
        viewModelScope.launch {
            listUiState = MovieListUiState.Loading

            try {
                val listResult = movieRepository.getDiscoverMovieList(page = 1)
                listUiState = MovieListUiState.Success(listResult)
            } catch (e: Exception) {
                listUiState = MovieListUiState.Error(e)
            }
        }
    }

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            detailUiState = MovieUiState.Loading

            try {
                val detailResult = movieRepository.getMovieDetail(id)
                detailUiState = MovieUiState.Success(detailResult)
            } catch (e: Exception) {
                detailUiState = MovieUiState.Error(e)
            }
        }
    }
}