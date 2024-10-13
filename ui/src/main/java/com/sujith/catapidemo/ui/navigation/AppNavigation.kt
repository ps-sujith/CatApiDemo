package com.sujith.catapidemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sujith.catapidemo.ui.feature_catDetail.CatDetailScreen
import com.sujith.catapidemo.ui.feature_catList.CatListScreen
import com.sujith.catapidemo.ui.feature_catList.CatListViewModel
import com.sujith.catapidemo.ui.feature_spalsh.SplashScreen
import com.sujith.catapidemo.ui.fetaure_favourite.FavouriteListScreen
import com.sujith.catapidemo.ui.fetaure_favourite.FavouriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Splash) {
        composable<Splash> {
            SplashScreen(navController)
        }
        navigation<CatListGraph>(startDestination = CatList) {
            composable<CatList> { entry ->
                val catListViewModel =
                    entry.sharedViewModel<CatListViewModel>(navController = navController)
                val catListUiState by catListViewModel.catListUiState.collectAsStateWithLifecycle()
                val favouriteViewModel: FavouriteViewModel = koinViewModel()
                val favListUiState by favouriteViewModel.favListUiState.collectAsStateWithLifecycle()
                val catList = catListUiState.catList.toMutableList()
                if (favListUiState.favList.isNotEmpty()) {
                    favListUiState.favList.forEach { cat ->
                        catList.takeIf { it.isNotEmpty() }
                            ?.find { it.breedName == cat.breedName }?.isFavourite = true
                    }
                }
                CatListScreen(
                    navController = navController,
                    catListUiState = catListUiState.copy(catList = catList)
                ) { isFavourite, catListItem ->
                    if (isFavourite) {
                        favouriteViewModel.addFavouriteCat(catListItem.copy(isFavourite = true))
                    } else {
                        favouriteViewModel.removeFavouriteCat(catListItem)
                    }
                }
            }
            composable<CatDetail> { entry ->
                val catListViewModel =
                    entry.sharedViewModel<CatListViewModel>(navController = navController)
                val catListUiState by catListViewModel.catListUiState.collectAsStateWithLifecycle()
                val catId = entry.toRoute<CatDetail>().id
                val selectedCatItem = catListUiState.catList.takeIf { it.isNotEmpty() }
                    ?.firstOrNull { it.id == catId }
                CatDetailScreen(navController = navController, selectedItem = selectedCatItem)
            }
        }

        navigation<FavouriteGraph>(startDestination = FavouriteList) {
            composable<FavouriteList> { entry ->
                val favouriteViewModel: FavouriteViewModel = koinViewModel()
                val favListUiState by favouriteViewModel.favListUiState.collectAsStateWithLifecycle()
                FavouriteListScreen(
                    navController = navController,
                    favListUiState = favListUiState
                ) { isFavourite, catListItem ->
                    if (isFavourite) {
                        favouriteViewModel.addFavouriteCat(catListItem.copy(isFavourite = true))
                    } else {
                        favouriteViewModel.removeFavouriteCat(catListItem)
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}