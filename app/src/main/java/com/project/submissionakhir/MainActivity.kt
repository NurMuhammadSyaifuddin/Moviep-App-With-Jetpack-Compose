package com.project.submissionakhir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.project.submissionakhir.ui.screen.movie.MovieViewModel
import com.project.submissionakhir.ui.screen.tvshow.TvShowViewModel
import com.project.submissionakhir.ui.theme.ColorPrimaryDark
import com.project.submissionakhir.ui.theme.SubmissionAkhirTheme
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val movieViewModel: MovieViewModel by viewModel()
    private val tvshowViewModel: TvShowViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SubmissionAkhirTheme {
                // A surface container using the 'background' color from the theme
                window.statusBarColor = ColorPrimaryDark.toArgb()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieApp(movieViewModel = movieViewModel, tvshowViewModel = tvshowViewModel)
                }
            }
        }
    }
}
