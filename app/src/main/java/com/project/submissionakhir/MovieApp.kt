package com.project.submissionakhir

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.submissionakhir.ui.navigation.NavigationItem
import com.project.submissionakhir.ui.navigation.Screen
import com.project.submissionakhir.ui.screen.detail.DetailScreen
import com.project.submissionakhir.ui.screen.movie.MovieScreen
import com.project.submissionakhir.ui.screen.movie.MovieViewModel
import com.project.submissionakhir.ui.screen.profile.ProfileScreen
import com.project.submissionakhir.ui.screen.tvshow.TvShowScreen
import com.project.submissionakhir.ui.screen.tvshow.TvShowViewModel
import com.project.submissionakhir.ui.theme.ColorPrimary
import com.project.submissionakhir.utils.encodedUrl

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    movieViewModel: MovieViewModel,
    tvshowViewModel: TvShowViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentState = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            var text: String? = null
            when (currentState) {
                Screen.Movie.route -> text = "Popular Movie"
                Screen.TvShow.route -> text = "Airing Today TV"
                Screen.Profile.route -> text = "Profile"
                Screen.DetailMovie.route -> text = "Detail Movie"
                Screen.DetailTvShow.route -> text = "Detail TV"
            }
            Box(
                modifier = modifier
                    .background(ColorPrimary)
                    .padding(16.dp)
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text.toString(),
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

        },
        bottomBar = {
            if (currentState != Screen.DetailMovie.route && currentState != Screen.DetailTvShow.route) BottomBar(
                navHostController = navController
            )
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Movie.route,
            modifier = modifier.padding(innerPadding)
        ) {

            composable(
                route = Screen.DetailMovie.route,
                arguments = listOf(
                    navArgument("name") {
                        type = NavType.StringType
                    },
                    navArgument("releaseDate") {
                        type = NavType.StringType
                    },
                    navArgument("overview") {
                        type = NavType.StringType
                    },
                    navArgument("backdrop") {
                        type = NavType.StringType
                    },
                    navArgument("poster") {
                        type = NavType.StringType
                    }
                )
            ) {
                val name = it.arguments?.getString("name")
                val releaseDate = it.arguments?.getString("releaseDate")
                val overview = it.arguments?.getString("overview")
                val backdrop = it.arguments?.getString("backdrop")
                val poster = it.arguments?.getString("poster")

                DetailScreen(
                    name = name.toString(),
                    releaseDate = releaseDate.toString(),
                    overview = overview,
                    backdrop = backdrop,
                    poster = poster
                ) {
                    navController.navigateUp()
                }
            }

            composable(
                route = Screen.DetailTvShow.route,
                arguments = listOf(
                    navArgument("title") {
                        type = NavType.StringType
                    },
                    navArgument("firstAirDate") {
                        type = NavType.StringType
                    },
                    navArgument("overview") {
                        type = NavType.StringType
                    },
                    navArgument("backdrop") {
                        type = NavType.StringType
                    },
                    navArgument("poster") {
                        type = NavType.StringType
                    }
                )
            ) {
                val title = it.arguments?.getString("title")
                val firstAirDate = it.arguments?.getString("firstAirDate")
                val overview = it.arguments?.getString("overview")
                val backdrop = it.arguments?.getString("backdrop")
                val poster = it.arguments?.getString("poster")

                DetailScreen(
                    name = title.toString(),
                    releaseDate = firstAirDate.toString(),
                    overview = overview,
                    backdrop = backdrop,
                    poster = poster
                ) {
                    navController.navigateUp()
                }
            }


            composable(Screen.Movie.route) {
                MovieScreen(
                    viewModel = movieViewModel,
                    navigateToDetail = { name, releaseDate, overview, backdrop, poster ->
                        navController.navigate(
                            Screen.DetailMovie.createRoute(
                                name,
                                releaseDate,
                                overview,
                                backdrop,
                                poster
                            )
                        )
                    })
            }
            composable(Screen.TvShow.route) {
                TvShowScreen(
                    viewModel = tvshowViewModel,
                    navigateToDetail = { title, firstAirDate, overview, backdrop, poster ->
                        navController.navigate(
                            Screen.DetailTvShow.createRoute(
                                title,
                                firstAirDate,
                                overview,
                                backdrop,
                                poster
                            )
                        )
                    })
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }

        }
    }
}

@Composable
fun BottomBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(modifier) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(id = R.string.movie),
                icon = ImageVector.vectorResource(id = R.drawable.ic_movie),
                screen = Screen.Movie
            ),
            NavigationItem(
                title = stringResource(id = R.string.tvshow),
                icon = ImageVector.vectorResource(id = R.drawable.ic_tvshow),
                screen = Screen.TvShow
            ),
            NavigationItem(
                title = stringResource(id = R.string.profile),
                icon = ImageVector.vectorResource(id = R.drawable.ic_profile),
                screen = Screen.Profile
            )
        )

        navigationItem.map { item ->
            BottomNavigationItem(
                icon = {
                    Icon(item.icon, contentDescription = item.title)
                },
                label = {
                    Text(text = item.title, fontFamily = FontFamily(Font(R.font.roboto_medium)))
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navHostController.navigate(item.screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )

        }

    }
}