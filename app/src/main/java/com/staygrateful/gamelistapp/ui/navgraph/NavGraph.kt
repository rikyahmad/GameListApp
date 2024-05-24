package com.staygrateful.gamelistapp.ui.navgraph

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.feature_list.presentation.view.GameListScreen
import com.staygrateful.feature_list.presentation.viewmodel.GameListViewModel
import com.staygrateful.feature_detail.presentation.view.GameDetailScreen
import com.staygrateful.feature_detail.presentation.viewmodel.GameDetailViewModel
import com.staygrateful.feature_favorites.presentation.view.GameFavoriteScreen
import com.staygrateful.feature_favorites.presentation.viewmodel.GameFavoriteViewModel
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    val gameListViewModel = viewModel<GameListViewModel>()
    val detailViewModel = viewModel<GameDetailViewModel>()
    val favoriteViewModel = viewModel<GameFavoriteViewModel>()

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = HomeScreen
        ) {
            composable<HomeScreen> {
                GameListScreen(modifier = Modifier
                    .fillMaxSize(),
                    viewmodel = gameListViewModel,
                    animatedVisibilityScope = this,
                    onItemClick = { data ->
                        navController.navigate(data.copy())
                    },
                    onFavoriteClick = {
                        println("Favorite Clicked!")
                        navController.navigate(FavoriteScreen)
                    }
                )
            }

            composable<GameEntity> {
                val args = it.toRoute<GameEntity>()
                GameDetailScreen(
                    data = args,
                    viewmodel = detailViewModel,
                    animatedVisibilityScope = this,
                    onBackPressed = {
                        navController.popBackStack()
                    },
                )
            }

            composable<FavoriteScreen> {
                GameFavoriteScreen(
                    viewmodel = favoriteViewModel,
                    animatedVisibilityScope = this,
                    onItemClick = { data ->
                        navController.navigate(data.copy())
                    },
                    onBackPressed = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}

@Serializable
object HomeScreen

@Serializable
object FavoriteScreen
