package com.sujith.catapidemo.ui.feature_catDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.ui.feature_catDetail.component.DetailsBottomViewComponent
import com.sujith.catapidemo.ui.feature_catDetail.component.DetailsTopViewComponent
import com.sujith.catapidemo.ui.utils.ErrorView
import com.sujith.catapidemo.ui.utils.TopAppBarComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreen(
    navController: NavController,
    selectedItem: CatListItem?,
    onFavouriteClicked: (isFavourite: Boolean, catListItem: CatListItem) -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            TopAppBarComponent(scrollBehaviour, true) {
                navController.navigateUp()
            }
        },
    ) { innerPadding ->
        if (selectedItem != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            ) {
                DetailsTopViewComponent(catItem = selectedItem) {
                    onFavouriteClicked(it, selectedItem)
                }
                DetailsBottomViewComponent(catItem = selectedItem)
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center

            ) {
                ErrorView(error = "No details found")
            }
        }
    }
}


