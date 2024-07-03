package com.a1573595.movies.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.a1573595.movies.R
import com.a1573595.movies.model.DiscoverMovie
import com.a1573595.movies.ui.theme.MoviesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieViewModel,
    onCellClick: (id: Int) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
            title = {
                Text(text = "Discover Movies", color = Color.White, textAlign = TextAlign.Center)
            },
        )
        when (viewModel.listUiState) {
            is MovieListUiState.Loading -> LoadingBody()
            is MovieListUiState.Success -> MovieListBody(viewModel.listUiState as MovieListUiState.Success, onCellClick)
            is MovieListUiState.Error -> ErrorBody((viewModel.listUiState as MovieListUiState.Error).throwable)
        }
    }
}

@Composable
fun LoadingBody() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(modifier = Modifier.fillMaxWidth(.2f))
    }
}

@Composable
fun MovieListBody(result: MovieListUiState.Success, onCellClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(result.list) { movie ->
            MovieCell(modifier = Modifier.clickable {
                onCellClick(movie.id)
            }, movie)
        }
    }
}

@Composable
fun MovieCell(
    modifier: Modifier,
    movie: DiscoverMovie
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data("https://image.tmdb.org/t/p/original${movie.posterPath}")
            .crossfade(true)
            .build(),
        contentDescription = movie.id.toString(),
        placeholder = painterResource(R.drawable.loading_img),
        /// 快取導致大小變更
//        contentScale = ContentScale.Crop,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
fun ErrorBody(throwable: Throwable) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_delete),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(.3f),
        )
        Text(
            text = throwable.message ?: "Unknown Error",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingBodyPreview() {
    MoviesTheme {
        LoadingBody()
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListBodyPreview() {
    MoviesTheme {
        val mockData = List(6) {
            DiscoverMovie(
                posterPath = "/posterPath",
                id = 16,
                title = "Title",
            )
        }
        MovieListBody(MovieListUiState.Success(mockData)) {}
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCellPreview() {
    MoviesTheme {
        MovieCell(
            modifier = Modifier, movie = DiscoverMovie(
                posterPath = "/Apr19lGxP7gm6y2HQX0kqOXTtqC.jpg",
                id = 16,
                title = "Inside Out 2",
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorBodyPreview() {
    MoviesTheme {
        ErrorBody(Exception("Exception"))
    }
}
