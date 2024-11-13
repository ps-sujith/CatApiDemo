package com.sujith.catapidemo.ui.feature_catList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.ui.R
import com.sujith.catapidemo.ui.feature_catList.component.CatListItemComponent
import com.sujith.catapidemo.ui.feature_catList.component.CatListUiState
import com.sujith.catapidemo.ui.navigation.CatDetail
import com.sujith.catapidemo.ui.utils.BottomNavBarComponent
import com.sujith.catapidemo.ui.utils.ErrorView
import com.sujith.catapidemo.ui.utils.TopAppBarComponent
import com.sujith.ui.utils.Lottie


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    navController: NavController,
    catListUiState: CatListUiState,
    onFavouriteClicked: (isFavourite: Boolean, catListItem: CatListItem) -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection).systemBarsPadding(),
        topBar = {
            TopAppBarComponent(scrollBehaviour, false) {}
        },
        bottomBar = {
            BottomNavBarComponent(navController)
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom =  innerPadding.calculateBottomPadding()).background( color = MaterialTheme.colorScheme.surfaceContainer),
            contentAlignment = Alignment.Center
        ) {
            if (catListUiState.isLoading) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceContainer
                ) {
                    Lottie(
                        rawFile = R.raw.loading_anim,
                        isPlaying = true,
                        iterations = Int.MAX_VALUE,
                        modifier = Modifier.padding(50.dp)
                    )
                }
            } else {
                if (catListUiState.catList.isNotEmpty()) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceContainer),
                        columns = GridCells.Fixed(1),
                        content = {
                            items(catListUiState.catList) { catItem ->
                                CatListItemComponent(
                                    catItem = catItem,
                                    onFavouriteClicked = onFavouriteClicked
                                ) { id ->
                                    navController.navigate(CatDetail(id = id, isInvokedFromFavourite = false))
                                }
                            }
                        }
                    )
                } else {
                    ErrorView(error = "Something Went wrong !!")
                }
            }
        }
    }
}
