package com.sujith.catapidemo.ui.feature_catDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Shapes
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.ui.R
import com.sujith.catapidemo.ui.feature_catDetail.component.DetailsBottomViewComponent
import com.sujith.catapidemo.ui.feature_catDetail.component.DetailsTopViewComponent
import com.sujith.catapidemo.ui.utils.ErrorView
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreen(
    navController: NavController,
    selectedItem: CatListItem?,
    onFavouriteClicked: (isFavourite: Boolean, catListItem: CatListItem) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        modifier = Modifier.background(Color.Transparent),
        properties = ModalBottomSheetProperties(shouldDismissOnBackPress = true),
        dragHandle = { BottomSheetDefaults.DragHandle(height = 0.dp, width = 0.dp) },
        shape = BottomSheetDefaults.HiddenShape,
        onDismissRequest = { navController.navigateUp() }) {
        if (selectedItem != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Icon(
                        Icons.Filled.Close,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Country of Origin",
                        modifier = Modifier
                            .clickable {
                                scope
                                    .launch {
                                        modalBottomSheetState.hide()
                                    }
                                    .invokeOnCompletion {
                                        navController.navigateUp()
                                    }
                            }
                            .height(40.dp)
                            .width(40.dp)
                    )
                }
                DetailsTopViewComponent(catItem = selectedItem) {
                    onFavouriteClicked(it, selectedItem)
                }
                DetailsBottomViewComponent(catItem = selectedItem)
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                ErrorView(error = "No details found")
            }
        }
    }
}




