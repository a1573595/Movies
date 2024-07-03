package com.a1573595.movies.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.a1573595.movies.R
import com.a1573595.movies.model.Movie
import com.a1573595.movies.ui.theme.MoviesTheme

@Composable
fun MovieDetailScreen(
    viewModel: MovieViewModel,
    onBackClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (viewModel.detailUiState) {
            is MovieUiState.Loading -> LoadingBody()
            is MovieUiState.Success -> {
                val movie = (viewModel.detailUiState as MovieUiState.Success).data
                DetailBody(movie)
            }

            is MovieUiState.Error -> ErrorBody((viewModel.detailUiState as MovieUiState.Error).throwable)
        }
        IconButton(onClick = onBackClick, modifier = Modifier
            .statusBarsPadding()
            .drawBehind {
                drawCircle(
                    color = Color.Black,
                    radius = this.size.maxDimension / 2 - 8
                )
            }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}

@Composable
fun DetailBody(movie: Movie) {
    val scroll = rememberScrollState(0)

    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("https://image.tmdb.org/t/p/original${movie.posterPath}")
//                .memoryCachePolicy(CachePolicy.DISABLED)
                .crossfade(true)
                .build(),
            contentDescription = movie.id.toString(),
            placeholder = painterResource(R.drawable.loading_img),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = movie.overview,
            color = Color.White,
            modifier = Modifier
                .verticalScroll(scroll)
                .padding(16.dp),
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000,
)
@Composable
fun DetailBodyPreview() {
    MoviesTheme {
        DetailBody(
            movie = Movie(
                posterPath = "/posterPath",
                id = 16,
                overview = "OverView",
                title = "Title",
            )
        )
    }
}