package com.sujith.catapidemo.ui.fetaure_favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.ui.R
import com.sujith.catapidemo.ui.feature_catList.component.FavListUiState
import com.sujith.catapidemo.ui.fetaure_favourite.component.FavListItemComponent
import com.sujith.catapidemo.ui.navigation.CatDetail
import com.sujith.catapidemo.ui.utils.BottomNavBarComponent
import com.sujith.catapidemo.ui.utils.TopAppBarComponent
import com.sujith.ui.utils.Lottie


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteListScreen(
    navController: NavController,
    favListUiState: FavListUiState,
    onFavouriteClicked: (isFavourite: Boolean, catListItem: CatListItem) -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            TopAppBarComponent(scrollBehaviour, false) {}
        },
        bottomBar = {
            BottomNavBarComponent(navController)
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (favListUiState.isLoading) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Lottie(
                        rawFile = R.raw.loading_anim,
                        isPlaying = true,
                        iterations = Int.MAX_VALUE,
                        modifier = Modifier.padding(50.dp)
                    )
                }
            } else {
                if (favListUiState.favList.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.surface)

                    ) {
                        Text(
                            text = "Your favourites.. ",
                            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                            fontSize = dimensionResource(R.dimen.large_font_size).value.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(
                                Font(R.font.title_regular, FontWeight.Light)
                            ),
                            style = TextStyle(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color.Red, Color.Yellow
                                    )
                                )
                            )
                        )
                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = dimensionResource(R.dimen.padding_small))
                                .background(MaterialTheme.colorScheme.surface),
                            columns = GridCells.Fixed(2),
                            content = {
                                items(favListUiState.favList) { favItem ->
                                    FavListItemComponent(
                                        catItem = favItem,
                                        onFavouriteClicked = onFavouriteClicked
                                    ) { id ->
                                        navController.navigate(
                                            CatDetail(
                                                id = id,
                                                isInvokedFromFavourite = true
                                            )
                                        )
                                    }
                                }
                            }
                        )
                    }

                } else {
                    Text(
                        text = "No favourite cats",
                        fontSize = dimensionResource(id = R.dimen.small_font_size).value.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = FontFamily.Serif,
                    )
                }
            }
        }
    }
}