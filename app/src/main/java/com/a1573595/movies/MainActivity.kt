package com.a1573595.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.a1573595.movies.ui.screen.MovieDetailScreen
import com.a1573595.movies.ui.screen.MovieListScreen
import com.a1573595.movies.ui.screen.MovieViewModel
import com.a1573595.movies.ui.theme.MoviesTheme

enum class MovieScreen {
    List,
    Detail;
}

/// todo paging3
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray,
                ) {
                    val viewModel: MovieViewModel = viewModel(factory = MovieViewModel.Factory)
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = MovieScreen.List.name,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        composable(MovieScreen.List.name) {
                            MovieListScreen(viewModel, onCellClick = { id ->
                                viewModel.getMovieDetail(id)
                                navController.safeNavigate(MovieScreen.Detail.name)
                            })
                        }

                        composable(MovieScreen.Detail.name) {
                            MovieDetailScreen(viewModel, onBackClick = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }
}


fun NavHostController.safeNavigate(
    route: String,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    if (currentDestination?.route !== route) {
        navigate(route, navOptions, navigatorExtras)
    }
}